package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.HotelReservation;
import lombok.Data;

import java.util.List;

@Data
public class HotelResponse {
    private String name;
    private String address;
    private List<HotelReservation> hotelReservations;
}
