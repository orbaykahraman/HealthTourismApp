package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.TravelPlanRequest;
import com.example.healthtourismapplication.model.responseDTO.TravelPlanResponse;
import com.example.healthtourismapplication.service.TravelPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    @PostMapping
    public ResponseEntity<TravelPlanResponse> createTravelPlan(@Valid TravelPlanRequest travelPlanRequest) {
        return travelPlanService.createTravelPlan(travelPlanRequest);
    }
}
