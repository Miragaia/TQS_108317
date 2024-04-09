package com.example.backend.unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.backend.cache.Cache;

public class CacheTest {

    private Cache cache;

    @BeforeEach
    void setUp() {
        cache = new Cache();
    }

    @Test
    @DisplayName("Test add and get from cache")
    void testAddToCacheAndGetFromCache() {
        cache.addToCache("USD_EUR", 0.92);
        assertEquals(0.92, cache.getFromCache("USD_EUR"));
    }

    @Test
    @DisplayName("Test contains item")
    void testContainsItem() {
        cache.addToCache("USD_EUR", 0.92);
        assertTrue(cache.containsItem("USD_EUR"));
    }

    @Test
    @DisplayName("Test get from cache with miss")
    void testMissCache() {
        assertNull(cache.getFromCache("USD_EUR"));
    }

    @Test
    @DisplayName("Test hits")
    void testHits() {
        cache.incrementHits();
        assertEquals(1, cache.getHits());
    }

    @Test
    @DisplayName("Test misses")
    void testMisses() {
        cache.incrementMisses();
        assertEquals(1, cache.getMisses());
    }

    @Test
    @DisplayName("Test requests")
    void testRequests() {
        cache.incrementRequests();
        assertEquals(1, cache.getRequests());
    }

    @Test
    @DisplayName("Test get hits")
    void testGetHits() {
        assertEquals(0, cache.getHits());
    }

    @Test
    @DisplayName("Test get misses")
    void testGetMisses() {
        assertEquals(0, cache.getMisses());
    }

    @Test
    @DisplayName("Test get requests")
    void testGetRequests() {
        assertEquals(0, cache.getRequests());
    }

    @Test
    @DisplayName("Test get cache size, with 4 values")
    void testGetCacheSize() {
        cache.addToCache("USD_EUR", 0.92);
        cache.addToCache("USD_GBP", 0.78);
        cache.addToCache("USD_JPY", 151.0);
        cache.addToCache("CAD_USD", 0.73);

        assertEquals(4, cache.getCacheSize());
    }
    
    @Test
    @DisplayName("Test TTL cache")
    void testCacheTimer() {
        cache.addToCache("USD_EUR", 0.92);
        cache.cacheTimer("USD_EUR", 50);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNull(cache.getFromCache("USD_EUR"));
    }

    @Test
    @DisplayName("Test clear cache")
    void testClearCache() {
        cache.addToCache("USD_EUR", 0.92);
        cache.addToCache("CAD_USD", 0.73);
        cache.clearCache();
        assertEquals(0, cache.getCacheSize());
    }
}
