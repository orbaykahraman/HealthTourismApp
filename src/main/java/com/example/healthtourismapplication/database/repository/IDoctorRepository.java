package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
}
