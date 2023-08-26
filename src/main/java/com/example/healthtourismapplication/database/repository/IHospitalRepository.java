package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHospitalRepository extends JpaRepository<Hospital, Long> {
}
