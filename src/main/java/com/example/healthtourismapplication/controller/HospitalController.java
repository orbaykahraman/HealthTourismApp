package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

}
