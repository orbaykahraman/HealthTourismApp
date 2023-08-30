package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByLocation(String location);
}
