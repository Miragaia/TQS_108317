package com.example.backend.unitTests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.backend.http.SimpleHttpClient;



public class ApiIResponseTest {

    private SimpleHttpClient httpClient;

    @BeforeEach
    public void setUp() {
        httpClient = new SimpleHttpClient();
    }

    @Test
    public void testGetResponse() throws IOException, ParseException {
        assertThat(httpClient.doHttpGet("https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_3ucCp3INikY7NxAxE8vKtPK5JqgE5DE01Se0D7h&currencies=USD&base_currency=EUR")).isNotNull();
    }

    @Test
    void testResponsetoInvalidURL() {
        assertThrows(IOException.class, () -> httpClient.doHttpGet("invalidURL"));
    }
}
