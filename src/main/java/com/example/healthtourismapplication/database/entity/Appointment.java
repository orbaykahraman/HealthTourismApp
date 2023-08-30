package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.model.enums.Status;
import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends BaseEntity {

    private Date appointmentDate;

    private Long patientId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String appointmentNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
}
