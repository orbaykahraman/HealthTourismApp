package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAirplaneRepository extends JpaRepository<Airplane, Long> {
    List<Airplane> findAirplanesByDestination(String destination);
}
