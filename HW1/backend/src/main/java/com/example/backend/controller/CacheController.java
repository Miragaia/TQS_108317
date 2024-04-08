package com.example.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.CacheService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

//will delete later, after testing cache


@RestController
public class CacheController {

    private final CacheService cacheService;
    private final ApplicationContext context;

    private static final Logger logger = LogManager.getLogger(CacheController.class);

    @Autowired
    public CacheController(CacheService cacheService, ApplicationContext context) {
        this.cacheService = cacheService;
        this.context = context;
    }

    @GetMapping("/inspect-cache/{cacheName}")
    public Map<Object, Object> inspectCache(@PathVariable String cacheName) {
        Map<Object, Object> cacheContents = new HashMap<>();
        CaffeineCacheManager cacheManager = (CaffeineCacheManager) context.getBean(CacheManager.class);
        Cache cache = cacheManager.getCache(cacheName);
        
        if (cache != null) {
            cacheContents = cacheService.inspectCache(cacheName);
            logger.info("Cache inspection completed for cache: " + cacheName);
            if (!cacheContents.isEmpty()) {
                System.out.println("Cache contents:");
                for (Map.Entry<Object, Object> entry : cacheContents.entrySet()) {
                    logger.info(entry.getKey() + " : " + entry.getValue());
                }
            } else {
                logger.info("Cache is empty.");
            }
        } else {
            logger.info("Cache with name '" + cacheName + "' does not exist.");
        }
        
        return cacheContents;
    }

    @GetMapping("/inspect-cache-managers")
    public Map<String, Object> inspectCacheManagers() {
        Map<String, Object> result = new HashMap<>();
        CaffeineCacheManager primaryCacheManager = context.getBean(CaffeineCacheManager.class);
        result.put("PrimaryCacheManager", inspectCacheManager(primaryCacheManager));
        logger.info("Cache managers inspection completed.");
        return result;
    }

    private Map<String, Object> inspectCacheManager(CaffeineCacheManager cacheManager) {
        Map<String, Object> cacheManagerInfo = new HashMap<>();
        cacheManagerInfo.put("CacheNames", cacheManager.getCacheNames());
        logger.info("Cache names: " + cacheManager.getCacheNames());
        return cacheManagerInfo;
    }
}
