package com.example.healthtourismapplication.model.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentRequest {
    @NotNull(message = "Appointment Date cannot be null.")
    private Date appointmentDate;
    @NotNull(message = "You need to specify doctor id.")
    private Long doctorId;
}
