package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.HotelReservation;
import com.example.healthtourismapplication.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHotelReservationRepository extends JpaRepository<HotelReservation, Long> {
    boolean existsHotelReservationByPatientIdAndStatusOrderByCreationDateDesc(Long patientId, Status status);

    List<HotelReservation> findAllByStatus(Status status);
}
