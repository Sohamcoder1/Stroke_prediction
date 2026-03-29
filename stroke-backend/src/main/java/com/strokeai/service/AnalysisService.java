package com.strokeai.service;

import com.strokeai.model.AnalysisResult;
import com.strokeai.model.Patient;

public interface AnalysisService {

    AnalysisResult analyzeScan(String filePath);

    AnalysisResult processScan(String filePath, Patient patient);

}