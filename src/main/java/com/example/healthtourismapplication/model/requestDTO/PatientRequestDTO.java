package com.example.healthtourismapplication.model.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PatientRequestDTO {
    @NotNull
    private String nameSurname;
    private int age;
    private String gender;
}
