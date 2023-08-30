package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.HotelReservation;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponse {
    private String name;
    private String location;
}
