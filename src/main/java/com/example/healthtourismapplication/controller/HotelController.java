package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.HotelRequest;
import com.example.healthtourismapplication.model.responseDTO.HotelResponse;
import com.example.healthtourismapplication.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody HotelRequest hotelRequest){
        return hotelService.createHotel(hotelRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long id){
       return hotelService.getHotelById(id);
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        return hotelService.getAllHotels();
    }

}
