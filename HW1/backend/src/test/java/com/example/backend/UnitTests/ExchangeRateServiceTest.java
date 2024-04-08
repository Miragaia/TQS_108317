package com.example.backend.UnitTests;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
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


    //testei exchangerate mas foi cache, talvez fazer para so o exchange rate
    @Test
    @DisplayName("Test get exchange rates cache")
    public void testGetExchangeRatesCache() {
        try {
            when(httpClient.doHttpGet(anyString())).thenReturn("{\"rates\":{\"USD\":1.0}}");
            double result = exchangeRateService.getExchangeRatesCache("EUR", "USD");
            assert(result == 1.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    //fazer testes para os erros?
}
