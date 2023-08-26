package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.HotelReservationRequest;
import com.example.healthtourismapplication.model.responseDTO.HotelReservationResponseDTO;
import com.example.healthtourismapplication.service.HotelReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel-reservation")
@RequiredArgsConstructor
public class HotelReservationController {
    private final HotelReservationService hotelReservationService;

    @PostMapping
    public ResponseEntity<HotelReservationResponseDTO> createHotelReservation(@RequestBody HotelReservationRequest hotelReservationRequest){
        return hotelReservationService.save(hotelReservationRequest);
    }
}
