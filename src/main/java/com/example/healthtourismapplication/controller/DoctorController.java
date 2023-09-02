package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.DoctorRequest;
import com.example.healthtourismapplication.model.responseDTO.DoctorResponse;
import com.example.healthtourismapplication.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable(name = "id") Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping()
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody @Valid DoctorRequest doctorRequest) {
        return doctorService.createDoctor(doctorRequest);

    }
    @PutMapping("/{hospitalId}/{doctorId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<DoctorResponse> addHospitalToDoctor(@PathVariable(name = "hospitalId") Long hospitalId,@PathVariable(name = "doctorId") Long doctorId){
        return doctorService.addHospitalToDoctor(hospitalId,doctorId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteDoctorById(@PathVariable(name = "id") Long id) {
        doctorService.deleteDoctorById(id);
    }

    @GetMapping("/locationAndDepartment")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public ResponseEntity<List<DoctorResponse>> findDoctorsByHospitalLocationAndDepartment(@RequestParam String location,@RequestParam String department) {
        return doctorService.findDoctorsByHospitalLocationAndDepartment(location,department);
    }
}
