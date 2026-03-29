package com.strokeai.util;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class ApiUtil {

    private static final String ML_URL = "http://localhost:8000/predict";

    public static Map<String, Object> callMLApi(String filePath) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> request = Map.of("filePath", filePath);

        return restTemplate.postForObject(ML_URL, request, Map.class);
    }
}