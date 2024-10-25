package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RateLimitedController {

    private final RateLimiterService rateLimiterService;

    public RateLimitedController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @GetMapping("/resource")
    public String getResource(@RequestParam String apiKey) {
        if (rateLimiterService.isAllowed(apiKey)) {
            return "Accessed Protected Resource!";
        } else {
            return "Rate limit exceeded. Please try again later.";
        }
    }
}