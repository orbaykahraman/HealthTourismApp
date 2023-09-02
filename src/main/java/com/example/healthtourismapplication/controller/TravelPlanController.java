package com.example.healthtourismapplication.controller;

import com.example.healthtourismapplication.model.requestDTO.TravelPlanRequest;
import com.example.healthtourismapplication.model.responseDTO.TravelPlanResponse;
import com.example.healthtourismapplication.service.TravelPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
@Tag(name = "Travel Plan")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PATIENT')")
    public ResponseEntity<TravelPlanResponse> createTravelPlan(@Valid TravelPlanRequest travelPlanRequest) {
        return travelPlanService.createTravelPlan(travelPlanRequest);
    }
}
