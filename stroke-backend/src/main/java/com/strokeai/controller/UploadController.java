package com.strokeai.controller;

import com.strokeai.dto.ResultResponse;
import com.strokeai.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private ReportService reportService;

    // ✅ UPLOAD
    @PostMapping("/upload")
    public ResultResponse upload(
            @RequestParam Long userId,
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String gender,
            @RequestParam MultipartFile file
    ) throws IOException {

        return reportService.upload(userId, name, age, gender, file);
    }

    // ✅ GET REPORTS (DASHBOARD 🔥)
    @GetMapping("/reports/{userId}")
    public List<?> getReports(@PathVariable Long userId) {
        return reportService.getReports(userId);
    }
}