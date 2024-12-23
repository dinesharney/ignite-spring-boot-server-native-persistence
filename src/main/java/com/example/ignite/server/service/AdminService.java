package com.example.ignite.server.service;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to handle business logic for Customer entities.
 */
@Service
public class AdminService {

    @Autowired
    private Ignite ignite;

    public void destroyCache(String cache){
        ignite.destroyCache(cache);
    }

    public void clearCache(String cache){
        IgniteCache<Object, Object> userCache = ignite.cache(cache);
        userCache.clear();
    }

}