package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.Appointment;
import com.example.healthtourismapplication.database.entity.Hospital;
import com.example.healthtourismapplication.database.entity.Patient;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private String nameSurname;
    private HospitalResponse hospital;
    private String department;
}
