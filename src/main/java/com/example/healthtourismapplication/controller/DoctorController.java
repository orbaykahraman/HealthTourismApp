package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/deneme")
    public ResponseEntity<String> deneme() {
        return ResponseEntity.ok("hello");
    }
}
