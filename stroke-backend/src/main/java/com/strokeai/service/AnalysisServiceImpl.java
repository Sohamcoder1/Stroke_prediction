package com.strokeai.service;

import com.strokeai.model.*;
import com.strokeai.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final ScanRepository scanRepo;
    private final AnalysisResultRepository resultRepo;
    private final MLService mlService;

    public AnalysisServiceImpl(ScanRepository scanRepo,
                               AnalysisResultRepository resultRepo,
                               MLService mlService) {
        this.scanRepo = scanRepo;
        this.resultRepo = resultRepo;
        this.mlService = mlService;
    }
    @Override
    public AnalysisResult analyzeScan(String filePath) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8000/predict";

        Map<String, String> request = new HashMap<>();
        request.put("filePath", filePath);

        AnalysisResult result = restTemplate.postForObject(
                url,
                request,
                AnalysisResult.class
        );

        return result;
    }

    @Override
    public AnalysisResult processScan(String filePath, Patient patient) {

        // 🟢 1. Save scan
        Scan scan = new Scan();
        scan.setFilePath(filePath);
        scan.setPatient(patient);
        scanRepo.save(scan);

        // 🟢 2. Call ML API
        Map<String, Object> response = mlService.analyze(filePath);

        // 🟢 3. Extract result
        String prediction = (String) response.get("prediction");
        Double confidence = Double.valueOf(response.get("confidence").toString());

        // 🟢 4. Save result
        AnalysisResult result = new AnalysisResult();
        result.setResult(prediction);
        result.setConfidence(confidence);
        result.setScan(scan);

        return resultRepo.save(result);
    }
}