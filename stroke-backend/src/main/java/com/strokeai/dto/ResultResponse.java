package com.strokeai.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {

    private Long patientId;
    private String patientName;
    private String result;
    private double confidence;
    private String filePath;
}