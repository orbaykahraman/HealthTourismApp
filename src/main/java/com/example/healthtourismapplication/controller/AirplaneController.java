package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.AirplaneRequest;
import com.example.healthtourismapplication.model.responseDTO.AirplaneResponse;
import com.example.healthtourismapplication.service.AirplaneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airplane")
@RequiredArgsConstructor
@Tag(name = "Airplane")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AirplaneResponse> createAirPlane(@RequestBody @Valid AirplaneRequest airplaneRequest){
        return airplaneService.createAirPlane(airplaneRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirplaneResponse> findAirPlaneById(@PathVariable Long id){
        return airplaneService.findAirPlaneById(id);
    }
    @GetMapping
    public ResponseEntity<List<AirplaneResponse>> findAllAirPlanes(){
        return airplaneService.findAllAirPlanes();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteAirPlaneById(@PathVariable(name = "id") Long id) {
        airplaneService.deleteAirPlaneById(id);
    }

    @GetMapping("/destination")
    public ResponseEntity<List<AirplaneResponse>> findAirplanesByDestination(@RequestParam String destination) {
        return airplaneService.findAirplanesByDestination(destination);
    }

}
