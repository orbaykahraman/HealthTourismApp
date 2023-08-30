package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.Hotel;
import com.example.healthtourismapplication.database.entity.Patient;
import com.example.healthtourismapplication.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelReservationResponseDTO {
    private Date reservationDate;
    private Patient patient;
    private HotelResponse hotel;
    @Enumerated(EnumType.STRING)
    private Status status;


}
