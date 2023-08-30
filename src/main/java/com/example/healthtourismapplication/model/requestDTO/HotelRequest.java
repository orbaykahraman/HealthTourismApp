package com.example.healthtourismapplication.model.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelRequest {
    @NotNull
    private String name;
    @NotNull
    private String location;
}
