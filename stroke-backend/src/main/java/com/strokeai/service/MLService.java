package com.strokeai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MLService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> analyze(String filePath) {

        String url = "http://localhost:8000/predict";

        // ✅ Send JSON properly
        Map<String, String> request = new HashMap<>();
        request.put("filePath", filePath);

        return restTemplate.postForObject(url, request, Map.class);
    }
}