package com.strokeai.model;

import jakarta.persistence.*;

@Entity
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result;
    private Double confidence;

    @OneToOne
    @JoinColumn(name = "scan_id")
    private Scan scan;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }

    public Scan getScan() { return scan; }
    public void setScan(Scan scan) { this.scan = scan; }
}