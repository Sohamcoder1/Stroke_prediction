package com.strokeai.repository;

import com.strokeai.model.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {

    // 🔥 IMPORTANT FOR DASHBOARD
    List<AnalysisResult> findByScan_Patient_User_Id(Long userId);
}