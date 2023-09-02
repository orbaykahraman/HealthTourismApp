package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.HotelRequest;
import com.example.healthtourismapplication.model.responseDTO.HotelResponse;
import com.example.healthtourismapplication.service.HotelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
@Tag(name = "Hotel")
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HotelResponse> createHotel(@RequestBody @Valid HotelRequest hotelRequest){
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteHotelById(@PathVariable(name = "id")Long id) {
        hotelService.deleteHotelById(id);
    }

    @GetMapping("/location")
    public ResponseEntity<List<HotelResponse>> findHotelsByLocation(@RequestParam String location) {
        return hotelService.findHotelsByLocation(location);
    }
}
