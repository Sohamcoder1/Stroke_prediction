package com.strokeai.controller;

import com.strokeai.dto.ResultResponse;
import com.strokeai.model.*;
import com.strokeai.repository.*;
import com.strokeai.util.ApiUtil;
import com.strokeai.util.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private ScanRepository scanRepo;

    @Autowired
    private AnalysisResultRepository resultRepo;

    @PostMapping("/upload")
    public ResultResponse uploadScan(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String gender,
            @RequestParam MultipartFile file
    ) throws IOException {

        // 1️⃣ Save file to disk
        String filePath = FileUtil.saveFile(file);

        // 2️⃣ Save patient info
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);
        patientRepo.save(patient);

        // 3️⃣ Save scan info
        Scan scan = new Scan();
        scan.setFilePath(filePath);
        scan.setPatient(patient);
        scanRepo.save(scan);

        // 4️⃣ Call ML API
        Map<String, Object> mlResponse = ApiUtil.callMLApi(filePath);
        String prediction = (String) mlResponse.get("prediction");
        Double confidence = Double.valueOf(mlResponse.get("confidence").toString());

        // 5️⃣ Save analysis result
        AnalysisResult result = new AnalysisResult();
        result.setResult(prediction);
        result.setConfidence(confidence);
        result.setScan(scan);
        resultRepo.save(result);

        // 6️⃣ Link result back to scan
        scan.setAnalysisResult(result);
        scanRepo.save(scan);

        // 7️⃣ Return response
        return new ResultResponse(
                patient.getId(),
                patient.getName(),
                prediction,
                confidence,
                filePath
        );
    }
}