package com.example.backend.service;


import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ExchangeRateService {

    @Autowired
    private RestTemplate restTemplate; // or use WebClient for non-blocking requests

    @Cacheable(value = "exchangeRates", key = "#baseCurrency")
    public Map<String, BigDecimal> getExchangeRates(String baseCurrency) {
        String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(apiUrl, Map.class);
        Map<String, BigDecimal> rates = (Map<String, BigDecimal>) responseEntity.getBody().get("rates");
        return rates;
    }
}

