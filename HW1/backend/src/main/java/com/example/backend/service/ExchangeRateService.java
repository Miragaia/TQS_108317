package com.example.backend.service;


import java.io.IOException;

import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.backend.cache.Cache;
import com.example.backend.http.HttpClient;


@Service
public class ExchangeRateService {

    private final HttpClient httpClient;
    private final Cache cache;

    private static final Logger logger = LogManager.getLogger(ExchangeRateService.class);

    public ExchangeRateService(HttpClient httpClient, Cache cache) {
        this.httpClient = httpClient;
        this.cache = cache;
    }
    
    public double getExchangeRatesCache(String baseCurrency, String targetCurrency) throws IOException, ParseException {
        logger.info("Fetching exchange rates cache from {} to {}.", baseCurrency, targetCurrency);
        String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_3ucCp3INikY7NxAxE8vKtPK5JqgE5DE01Se0D7hL";
        try {
            String key = baseCurrency + "_" + targetCurrency;

            Double ExchangeRate = cache.getFromCache(key);

            if(ExchangeRate != null) {
                logger.info("Cache hit to exchange rate value from {} to {}: {}", baseCurrency, targetCurrency, ExchangeRate);
                return ExchangeRate;
            } else {
                logger.info("Cache miss! Now fetching from API");
                String requestUrl = apiUrl + "&currencies=" + targetCurrency + "&base_currency=" + baseCurrency ;
                String response = httpClient.doHttpGet(requestUrl);
                double exchangeRate = parseExchangeRateFromResponse(response, targetCurrency);

                logger.info("Exchange rate from {} to {} is {} retrieved from API", baseCurrency, targetCurrency, exchangeRate);

                cache.addToCache(key, exchangeRate);
                cache.cacheTimer(key, 600000);

                logger.info("Cache updated to exchange rate value from {} to {}: {}", baseCurrency, targetCurrency, exchangeRate);

                return exchangeRate;
            }

        } catch (Exception e) {
            logger.error("Error occurred while fetching cache exchange rates", e);
            return 0.0;
        }
    }

    private double parseExchangeRateFromResponse(String response, String targetCurrency) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        JSONObject data = (JSONObject) jsonObject.get("data");
        Double Rate = (Double) data.get(targetCurrency);
        return Rate != null ? Rate : 0.0;
    }
}


