package com.example.healthtourismapplication.model.requestDTO;

import com.example.healthtourismapplication.database.entity.Hotel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
public class HotelReservationRequest {
    @NotNull
    private Date reservationDate;
    @NotNull
    private Long hotelId;
}
