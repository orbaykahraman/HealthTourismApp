package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.AppointmentRequest;
import com.example.healthtourismapplication.model.responseDTO.AppointmentResponse;
import com.example.healthtourismapplication.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;


    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable(name = "id") Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest) {
        return appointmentService.createAppointment(appointmentRequest);
    }

    @GetMapping("/myAppointments")
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public ResponseEntity<List<AppointmentResponse>> getMyAppointments() {
        return appointmentService.getMyAppointments();
    }

    @GetMapping("/appointmentsOfDoctor")
    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsOfDoctor() {
        return appointmentService.getAppointmentsOfDoctor();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteAppointmentById(@PathVariable(name = "id")Long id) {
        appointmentService.deleteAppointmentById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    public ResponseEntity<AppointmentResponse> setAppointmentNotesById(@PathVariable(name = "id")Long id,@RequestParam String appointmentNotes) {
        return appointmentService.setAppointmentNotesById(id,appointmentNotes);
    }
}
