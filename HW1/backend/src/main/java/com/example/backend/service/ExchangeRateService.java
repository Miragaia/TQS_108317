package com.example.backend.service;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.cache.annotation.CacheEvict;

import com.example.backend.config.CacheConfig;
import com.example.backend.http.HttpClient;

@Service
public class ExchangeRateService {

    private final HttpClient httpClient;
    private final Map<String, Double> CacheExchangeRate;

    private static final Logger logger = LogManager.getLogger(ExchangeRateService.class);

    public ExchangeRateService(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.CacheExchangeRate = new HashMap<>();
    }
    
    @Cacheable(value = "exchangeRates", key = "#baseCurrency + '_' + #targetCurrency")
    public double getExchangeRatesCache(String baseCurrency, String targetCurrency) throws IOException, ParseException {
        logger.info("Fetching exchange rates cache from {} to {}.", baseCurrency, targetCurrency);
        String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_3ucCp3INikY7NxAxE8vKtPK5JqgE5DE01Se0D7hL";
        try {
            String key = baseCurrency + "_" + targetCurrency;
            
            if (CacheExchangeRate.containsKey(key)) {
                logger.info("Cache updated to exchange rate value from {} to {}: {}", baseCurrency, targetCurrency, CacheExchangeRate.get(key));
                return CacheExchangeRate.get(key);
            } else {
                logger.info("Error fetching cache, fetching from API");
                String requestUrl = apiUrl + "&currencies=" + targetCurrency + "&base_currency=" + baseCurrency ;
                String response = httpClient.doHttpGet(requestUrl);
                double exchangeRate = parseExchangeRateFromResponse(response, targetCurrency);

                logger.info("Exchange rate from {} to {} is {} retrieved from API", baseCurrency, targetCurrency, exchangeRate);

                CacheExchangeRate.put(key, exchangeRate);

                logger.info("Cache updated to exchange rate value from {} to {}: {}", baseCurrency, targetCurrency, exchangeRate);

                return exchangeRate;
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching cache exchange rates", e);
            return 0.0;
        }
    }


    @CacheEvict(cacheNames = "exchangeRates", allEntries = true)
    public void clearCache() {
        CacheExchangeRate.clear();
    }

    private double parseExchangeRateFromResponse(String response, String targetCurrency) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        JSONObject data = (JSONObject) jsonObject.get("data");
        Double Rate = (Double) data.get(targetCurrency);
        return Rate != null ? Rate : 0.0;
    }
}


