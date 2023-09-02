package com.example.healthtourismapplication.model.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorRequest {
    @NotNull
    private String nameSurname;
    @NotNull
    private Long hospitalId;
    @NotNull
    private String department;

}
