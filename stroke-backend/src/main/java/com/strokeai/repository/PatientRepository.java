package com.strokeai.repository;

import com.strokeai.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository   // ✅ ADD THIS
public interface PatientRepository extends JpaRepository<Patient, Long> {
}