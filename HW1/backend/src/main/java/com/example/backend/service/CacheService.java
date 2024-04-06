package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {

    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Map<Object, Object> inspectCache(String cacheName) {
        Map<Object, Object> cacheContents = new HashMap<>();
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            Object nativeCache = cache.getNativeCache();
            if (nativeCache instanceof Map) {
                cacheContents.putAll((Map<?, ?>) nativeCache);
            }
        }
        return cacheContents;
    }
}


