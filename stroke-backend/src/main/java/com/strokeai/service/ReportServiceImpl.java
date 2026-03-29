package com.strokeai.service;

import com.strokeai.dto.ResultResponse;
import com.strokeai.model.*;
import com.strokeai.repository.*;
import com.strokeai.util.ApiUtil;
import com.strokeai.util.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired private UserRepository userRepo;
    @Autowired private PatientRepository patientRepo;
    @Autowired private ScanRepository scanRepo;
    @Autowired private AnalysisResultRepository resultRepo;

    @Override
    public ResultResponse upload(Long userId, String name, int age,
                                 String gender, MultipartFile file) throws IOException {

        User user = userRepo.findById(userId).orElse(null);
        if (user == null) throw new RuntimeException("User not found");

        String filePath = FileUtil.saveFile(file);

        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setUser(user);
        patientRepo.save(patient);

        Scan scan = new Scan();
        scan.setFilePath(filePath);
        scan.setPatient(patient);
        scanRepo.save(scan);

        Map<String, Object> response = ApiUtil.callMLApi(filePath);

        String prediction = (String) response.get("prediction");
        Double confidence = Double.valueOf(response.get("confidence").toString());

        AnalysisResult result = new AnalysisResult();
        result.setResult(prediction);
        result.setConfidence(confidence);
        result.setScan(scan);
        resultRepo.save(result);

        return new ResultResponse(
                patient.getId(),
                patient.getName(),
                prediction,
                confidence,
                filePath
        );
    }

    @Override
    public List<AnalysisResult> getReports(Long userId) {
        return resultRepo.findByScan_Patient_User_Id(userId);
    }
}