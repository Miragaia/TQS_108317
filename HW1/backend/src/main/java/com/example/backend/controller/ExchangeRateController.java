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

import java.io.IOException;
import org.json.simple.parser.ParseException;

@RestController
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    private static final Logger logger = LogManager.getLogger(ExchangeRateController.class);

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/cache-exchange-rates/{baseCurrency}/{targetCurrency}")
    public double cacheExchangeRatesForCurrency(@PathVariable String baseCurrency, @PathVariable String targetCurrency) throws ParseException, IOException {
        logger.info("Caching exchange rates from {} to {}.", baseCurrency, targetCurrency);
        return exchangeRateService.getExchangeRatesCache(baseCurrency, targetCurrency);
    }
}