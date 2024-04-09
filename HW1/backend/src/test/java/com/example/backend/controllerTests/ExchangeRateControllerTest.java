package com.example.backend.controllerTests;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.backend.cache.Cache;
import com.example.backend.controller.ExchangeRateController;
import com.example.backend.service.ExchangeRateService;

@WebMvcTest(ExchangeRateController.class)
class ExchangeRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @MockBean
    private Cache cache;

    @Test
    @DisplayName("Test cacheExchangeRatesForCurrency")
    void testCacheExchangeRatesForCurrency() throws Exception {
        // Arrange
        String baseCurrency = "USD";
        String targetCurrency = "EUR";
        double exchangeRate = 0.92;
        when(exchangeRateService.getExchangeRatesCache(baseCurrency, targetCurrency)).thenReturn(exchangeRate);

        // Act
        mockMvc.perform(get("/cache-exchange-rates/{baseCurrency}/{targetCurrency}", baseCurrency, targetCurrency))
                .andExpect(status().isOk());

        // Assert
        verify(exchangeRateService, times(1)).getExchangeRatesCache(baseCurrency, targetCurrency);
    }
    
}
