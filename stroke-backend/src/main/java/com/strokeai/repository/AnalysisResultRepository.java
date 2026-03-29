package com.strokeai.repository;

import com.strokeai.model.AnalysisResult;
import com.strokeai.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {

    // 🔍 Get result by scan
    AnalysisResult findByScan(Scan scan);

    // 🔍 Get all results with specific prediction
    List<AnalysisResult> findByResult(String result);
}