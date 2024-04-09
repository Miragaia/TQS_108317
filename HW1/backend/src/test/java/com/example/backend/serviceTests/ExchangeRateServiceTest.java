package com.example.backend.serviceTests;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.backend.http.HttpClient;
import com.example.backend.service.ExchangeRateService;


@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {

    //testes para a API

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private ExchangeRateService exchangeRateService;


    // @Test
    // @DisplayName("Testando a API de exchange rates")
    // public void testGetExchangeRatesCache() throws Exception {
    //     // Given
    //     String baseCurrency = "USD";
    //     String targetCurrency = "BRL";
    //     String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_3ucCp3INikY7NxAxE8vKtPK5JqgE5DE01Se0D7hL";
    //     String key = baseCurrency + "_" + targetCurrency;

    //     String response = "{\"data\":{\"USD\":1.0,\"BRL\":5.5}}"; // Sample API response
        
    //     when(httpClient.doHttpGet(apiUrl + "&currencies=" + targetCurrency + "&base_currency=" + baseCurrency)).thenReturn(response);

    //     // When
    //     double exchangeRate = exchangeRateService.getExchangeRatesCache(baseCurrency, targetCurrency);

    //     // Then
    //     assertThat(exchangeRate).isEqualTo(5.5);
    // }
}
