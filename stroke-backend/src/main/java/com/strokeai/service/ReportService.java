package com.strokeai.service;

import com.strokeai.dto.ResultResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReportService {

    ResultResponse upload(Long userId, String name, int age,
                          String gender, MultipartFile file) throws IOException;

    List<?> getReports(Long userId);
}