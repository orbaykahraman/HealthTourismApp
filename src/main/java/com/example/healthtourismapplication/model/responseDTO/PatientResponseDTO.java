package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.Appointment;
import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.database.entity.FlightInformation;
import com.example.healthtourismapplication.database.entity.HotelReservation;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO {
    private String nameSurname;
    private int age;
    private String gender;
}
