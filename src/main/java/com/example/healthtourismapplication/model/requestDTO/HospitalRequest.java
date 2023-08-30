package com.example.healthtourismapplication.model.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HospitalRequest {
    @NotNull
    private String name;
    @NotNull
    private String location;
}
