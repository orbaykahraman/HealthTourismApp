package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Appointment extends BaseEntity {
    private Date appointmentDate;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
