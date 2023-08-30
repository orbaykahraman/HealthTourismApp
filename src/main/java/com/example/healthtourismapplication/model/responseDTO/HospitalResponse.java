package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.Doctor;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalResponse {
    private String name;
    private String location;
}
