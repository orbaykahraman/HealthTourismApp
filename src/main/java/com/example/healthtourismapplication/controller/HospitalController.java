package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.HospitalRequest;
import com.example.healthtourismapplication.model.responseDTO.HospitalResponse;
import com.example.healthtourismapplication.service.HospitalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
@Tag(name = "Hospital")
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HospitalResponse> createHospital(@RequestBody @Valid HospitalRequest hospitalRequest){
        return hospitalService.createHospital(hospitalRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id);
    }

    @GetMapping
    public ResponseEntity<List<HospitalResponse>> findAllHospitals(){
        return hospitalService.findAllHospitals();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteHospitalById(@PathVariable(name = "id")Long id) {
        hospitalService.deleteHospitalById(id);
    }

    @GetMapping("/location")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public ResponseEntity<List<HospitalResponse>> findHospitalsByLocation(@RequestParam String location) {
        return hospitalService.findHospitalsByLocation(location);
    }
}
