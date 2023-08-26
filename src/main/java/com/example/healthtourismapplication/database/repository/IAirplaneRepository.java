package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAirplaneRepository extends JpaRepository<Airplane, Long> {
}
