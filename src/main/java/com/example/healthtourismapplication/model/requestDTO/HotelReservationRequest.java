package com.example.healthtourismapplication.model.requestDTO;

import com.example.healthtourismapplication.database.entity.Hotel;
import lombok.Data;

import java.util.Date;
@Data
public class HotelReservationRequest {
    private Date reservationDate;
    private Long hotelId;
}
