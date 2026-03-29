package com.strokeai.repository;

import com.strokeai.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // 🔍 Find patient by name
    List<Patient> findByName(String name);

    // 🔍 Find patient by gender
    List<Patient> findByGender(String gender);

    // 🔍 Find patient by age greater than
    List<Patient> findByAgeGreaterThan(int age);
}