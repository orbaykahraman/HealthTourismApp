package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelReservationRepository extends JpaRepository<HotelReservation, Long> {
}
