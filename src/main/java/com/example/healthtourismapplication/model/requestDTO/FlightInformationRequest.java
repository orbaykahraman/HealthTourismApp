package com.example.healthtourismapplication.model.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class FlightInformationRequest {
    private String name;
    private Date flightDate;
    @NotNull
    private Long airplaneId;
}
