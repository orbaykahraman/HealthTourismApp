package com.example.healthtourismapplication.model.requestDTO;

import lombok.Data;


@Data
public class PatientRequestDTO {
    private String nameSurname;
    private int age;
    private String gender;
}
