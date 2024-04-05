package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.ExchangeRateService;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    private static final Logger logger = LogManager.getLogger(ExchangeRateController.class);

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/test-exchange/{baseCurrency}")
    public Map<String, BigDecimal> testExchangeRates(@PathVariable String baseCurrency) {
        logger.info("Fetching exchange rates for base currency {}.", baseCurrency);
        logger.info("Exchange rates: " + exchangeRateService.getExchangeRates(baseCurrency));
        return exchangeRateService.getExchangeRates(baseCurrency);
    }
}
