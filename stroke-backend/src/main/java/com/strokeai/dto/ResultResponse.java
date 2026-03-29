package com.strokeai.dto;

public class ResultResponse {

    private Long patientId;
    private String name;
    private String result;
    private Double confidence;
    private String filePath;

    public ResultResponse(Long patientId, String name,
                          String result, Double confidence,
                          String filePath) {
        this.patientId = patientId;
        this.name = name;
        this.result = result;
        this.confidence = confidence;
        this.filePath = filePath;
    }

    // GETTERS
    public Long getPatientId() { return patientId; }
    public String getName() { return name; }
    public String getResult() { return result; }
    public Double getConfidence() { return confidence; }
    public String getFilePath() { return filePath; }
}