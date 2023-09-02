package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.FlightInformationRequest;
import com.example.healthtourismapplication.model.responseDTO.FlightInformationResponse;
import com.example.healthtourismapplication.service.FlightInformationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightInformation")
@RequiredArgsConstructor
@Tag(name = "Flight Information")
public class FlightInformationController {
    private final FlightInformationService flightInformationService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public ResponseEntity<FlightInformationResponse> createFlight(@RequestBody @Valid FlightInformationRequest flightInformationRequest) {
        return flightInformationService.createFlight(flightInformationRequest);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public void deleteFlightInfoById(@PathVariable(name = "id") Long id) {
         flightInformationService.deleteById(id);
    }
}
