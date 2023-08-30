package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Appointment;
import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctor(Doctor doctor);

    @Query(value = "select a from Appointment a where a.patientId = ?1 and a.status = 'WAITING' order by a.creationDate desc LIMIT 1",nativeQuery = true)
    Appointment findLastAppointmentByPatientId(Long patientId);

    List<Appointment> findAllByStatus(Status status);

}
