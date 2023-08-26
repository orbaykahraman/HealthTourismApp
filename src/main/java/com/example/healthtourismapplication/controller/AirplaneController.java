package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airplane")
@RequiredArgsConstructor
public class AirplaneController {

    private final AirplaneService airplaneService;
}
