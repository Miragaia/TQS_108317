package com.example.backend.service;


import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class ExchangeRateService {

    private final RestTemplate restTemplate;

    private static final Logger logger = LogManager.getLogger(ExchangeRateService.class);

    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // public Map<String, BigDecimal> getExchangeRates(String baseCurrency) {
    //     String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_3ucCp3INikY7NxAxE8vKtPK5JqgE5DE01Se0D7hL";
    //     ResponseEntity<Map> responseEntity = restTemplate.getForEntity(apiUrl, Map.class);
    //     Map<String, BigDecimal> rates = (Map<String, BigDecimal>) responseEntity.getBody().get("rates");
    //     return rates;
    // }

    public Map<String, BigDecimal> getExchangeRates(String baseCurrency) {
        String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_3ucCp3INikY7NxAxE8vKtPK5JqgE5DE01Se0D7hL";
        try {
            ResponseEntity<Map> responseEntity = restTemplate.getForEntity(apiUrl, Map.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                Map<String, BigDecimal> rates = (Map<String, BigDecimal>) responseEntity.getBody().get("data");
                logger.info("Fetched exchange rates: {}", rates);
                return rates;
            } else {
                // Log error or handle non-successful response
                logger.error("Failed to fetch exchange rates. Status code: {}", responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            // Log error or handle exception
            logger.error("Error occurred while fetching exchange rates", e);
        }
        return null; // Return null if there's an error
    }
    
}


