package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TravelPlanResponse {

    private Long patientId;
    private AppointmentResponse appointment;
    private HotelReservationResponseDTO hotelReservation;
    private FlightInformationResponse flightInformation;

    @Enumerated(EnumType.STRING)
    private Status status;
}
