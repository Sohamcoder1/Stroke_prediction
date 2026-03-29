package com.strokeai.repository;

import com.strokeai.model.Scan;
import com.strokeai.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScanRepository extends JpaRepository<Scan, Long> {

    // 🔍 Get all scans of a patient
    List<Scan> findByPatient(Patient patient);

    // 🔍 Get scans by file path
    List<Scan> findByFilePath(String filePath);
}