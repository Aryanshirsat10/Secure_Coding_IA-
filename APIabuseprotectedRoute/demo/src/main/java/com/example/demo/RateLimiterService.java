package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class RateLimiterService {

    private static final int MAX_REQUESTS = 5;     
    private static final long TIME_WINDOW = 60_000; 

    private final ConcurrentHashMap<String, RequestData> requestMap = new ConcurrentHashMap<>();

    public boolean isAllowed(String apiKey) {
        RequestData requestData = requestMap.getOrDefault(apiKey, new RequestData(0, System.currentTimeMillis()));

        if (System.currentTimeMillis() - requestData.startTime > TIME_WINDOW) {
            requestData.startTime = System.currentTimeMillis();
            requestData.requestCount = 0;
        }

        if (requestData.requestCount < MAX_REQUESTS) {
            requestData.requestCount++;
            requestMap.put(apiKey, requestData);
            return true;
        }
        
        return false;
    }

    private static class RequestData {
        int requestCount;
        long startTime;

        RequestData(int requestCount, long startTime) {
            this.requestCount = requestCount;
            this.startTime = startTime;
        }
    }
}