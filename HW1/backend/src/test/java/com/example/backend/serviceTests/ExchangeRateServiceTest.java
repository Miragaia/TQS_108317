package com.example.backend.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.backend.cache.Cache;
import com.example.backend.http.SimpleHttpClient;
import com.example.backend.service.ExchangeRateService;

class ExchangeRateServiceTest {

    private ExchangeRateService exchangeRateService;
    private SimpleHttpClient simpleHttpClient;
    private Cache cache;

    @BeforeEach
    public void setUp() {
        simpleHttpClient = mock(SimpleHttpClient.class);
        cache = mock(Cache.class);
        exchangeRateService = new ExchangeRateService(simpleHttpClient, cache);
    }

    @Test
    @DisplayName("Test getExchangeRate in cache")
    void testGetExchangeRateInCache() throws IOException, ParseException {
        String baseCurrency = "USD";
        String targeCurrency = "EUR";
        double exchangeRate = 0.92;
        when(cache.getFromCache("USD_EUR")).thenReturn(exchangeRate);
        double exchangeRateResult = exchangeRateService.getExchangeRatesCache(baseCurrency, targeCurrency);

        assertEquals(0.92, exchangeRateResult);
        verify(cache, times(1)).getFromCache("USD_EUR");
        verifyNoInteractions(simpleHttpClient);
    }

    @Test
    @DisplayName("Test getExchangeRate not in cache")
    void testGetExchangeRateNotInCache() throws IOException, ParseException {
        String baseCurrency = "USD";
        String targeCurrency = "EUR";
        double exchangeRate = 0.92;
        when(cache.getFromCache("USD_EUR")).thenReturn(null);
        when(simpleHttpClient.doHttpGet(anyString())).thenReturn("{\"data\":{\"EUR\":0.92}}");
        double exchangeRateResult = exchangeRateService.getExchangeRatesCache(baseCurrency, targeCurrency);

        assertEquals(0.92, exchangeRateResult);
        verify(cache, times(1)).getFromCache("USD_EUR");
        verify(simpleHttpClient, times(1)).doHttpGet(anyString());
        verify(cache, times(1)).addToCache("USD_EUR", exchangeRate);
    }
    
    
}
