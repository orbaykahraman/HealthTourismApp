package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.FlightInformation;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneResponse {
    private String name;
    private Long seatNumber;
    private Long remainingSeats;
    private Date flightDate;
    private List<FlightInformationResponse> flightInformations;
    private String destination;
}
