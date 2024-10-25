package com.example.demo1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VulnerableController {

    @GetMapping("/vulnerable-resource")
    public String getVulnerableResource() {
        // No rate limiting, IP tracking, or user authentication
        return "This is an unprotected resource.";
    }
}
