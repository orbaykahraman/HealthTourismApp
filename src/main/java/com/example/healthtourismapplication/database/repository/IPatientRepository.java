package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
}
