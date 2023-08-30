package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {

    private Date appointmentDate;
    private Long patientId;
    private DoctorResponse doctor;
    private String appointmentNotes;

    @Enumerated(EnumType.STRING)
    private Status status;

}
