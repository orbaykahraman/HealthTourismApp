package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}
