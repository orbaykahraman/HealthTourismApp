package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.Hotel;
import com.example.healthtourismapplication.database.entity.Patient;
import lombok.Data;

import java.util.Date;

@Data
public class HotelReservationResponseDTO {
    private Date reservationDate;
    private Patient patient;
    private Hotel hotel;

}
