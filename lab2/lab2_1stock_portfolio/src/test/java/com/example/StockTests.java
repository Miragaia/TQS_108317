package com.example;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StockTests {
    @Mock
    IStockmarketService stockmarket;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    public void testGetTotalValue() {
        when(stockmarket.lookUpPrice("EBAY")).thenReturn(7.0);
        when(stockmarket.lookUpPrice("GOOGLE")).thenReturn(9.0);

        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("GOOGLE", 3));

        assertEquals(41, portfolio.getTotalValue());
    }
}
