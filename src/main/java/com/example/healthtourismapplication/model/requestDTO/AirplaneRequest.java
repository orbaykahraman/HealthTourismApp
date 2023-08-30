package com.example.healthtourismapplication.model.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AirplaneRequest {
    @NotNull(message = "Name cannot be empty.")
    private String name;
    @NotNull(message = "You must enter seat number.")
    private Long seatNumber;
    @NotNull
    private Date flightDate;
    @NotNull
    private String destination;
}
