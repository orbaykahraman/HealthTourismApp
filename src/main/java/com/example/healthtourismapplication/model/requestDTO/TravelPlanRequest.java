package com.example.healthtourismapplication.model.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TravelPlanRequest {

    @NotNull
    private Long appointmentId;
    @NotNull
    private Long hotelReservationId;
    @NotNull
    private Long flightInformationId;
}
