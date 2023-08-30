package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightInformationResponse {
    private String name;
    private Date flightDate;
    @Enumerated(EnumType.STRING)
    private Status status;
}
