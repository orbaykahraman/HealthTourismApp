package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.FlightInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightInformationRepository extends JpaRepository<FlightInformation, Long> {
}
