package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.HotelReservationRequest;
import com.example.healthtourismapplication.model.responseDTO.HotelReservationResponseDTO;
import com.example.healthtourismapplication.service.HotelReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel-reservation")
@RequiredArgsConstructor
public class HotelReservationController {
    private final HotelReservationService hotelReservationService;

    @PostMapping
    public ResponseEntity<HotelReservationResponseDTO> createHotelReservation(@RequestBody @Valid HotelReservationRequest hotelReservationRequest){
        return hotelReservationService.createHotelReservation(hotelReservationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteHotelReservationById(@PathVariable(name = "id") Long id) {
        hotelReservationService.deleteHotelReservationById(id);
    }
}
