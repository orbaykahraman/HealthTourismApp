package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.service.HotelReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel-reservation")
@RequiredArgsConstructor
public class HotelReservationController {
    private final HotelReservationService hotelReservationService;
}
