package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.FlightInformation;
import com.example.healthtourismapplication.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFlightInformationRepository extends JpaRepository<FlightInformation, Long> {
    List<FlightInformation> findAllByStatus(Status status);
}
