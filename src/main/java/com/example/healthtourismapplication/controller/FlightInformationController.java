package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.service.FlightInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flightInformation")
@RequiredArgsConstructor
public class FlightInformationController {
    private final FlightInformationService flightInformationService;
}
