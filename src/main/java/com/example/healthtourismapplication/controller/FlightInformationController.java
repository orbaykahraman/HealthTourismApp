package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.FlightInformationRequest;
import com.example.healthtourismapplication.model.responseDTO.FlightInformationResponse;
import com.example.healthtourismapplication.service.FlightInformationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightInformation")
@RequiredArgsConstructor
public class FlightInformationController {
    private final FlightInformationService flightInformationService;

    @PostMapping
    public ResponseEntity<FlightInformationResponse> createFlight(@RequestBody @Valid FlightInformationRequest flightInformationRequest) {
        return flightInformationService.createFlight(flightInformationRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteFlightInfoById(@PathVariable(name = "id") Long id) {
         flightInformationService.deleteById(id);
    }
}
