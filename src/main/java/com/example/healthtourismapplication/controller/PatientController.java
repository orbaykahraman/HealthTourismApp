package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.PatientRequestDTO;
import com.example.healthtourismapplication.model.responseDTO.PatientResponseDTO;
import com.example.healthtourismapplication.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;


    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO patientRequestDTO) {
        return patientService.createPatient(patientRequestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable(name = "id") Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return patientService.getAllPatients();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PATIENT')")
    public void deletePatientById(@PathVariable(name = "id") Long id) {
        patientService.deletePatientById(id);
    }

//    @PutMapping("/{doctorId}")
//    public ResponseEntity<PatientResponseDTO> addDoctorToPatient(@PathVariable(name = "doctorId") Long id) {
//
//    }
}
