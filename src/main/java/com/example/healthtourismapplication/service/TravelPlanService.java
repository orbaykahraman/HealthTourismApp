package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.*;
import com.example.healthtourismapplication.database.repository.ITravelPlanRepository;
import com.example.healthtourismapplication.exception.CheckIsNotSuccessException;
import com.example.healthtourismapplication.mapper.TravelPlanMapper;
import com.example.healthtourismapplication.model.enums.Status;
import com.example.healthtourismapplication.model.requestDTO.TravelPlanRequest;
import com.example.healthtourismapplication.model.responseDTO.TravelPlanResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TravelPlanService {

    private final ITravelPlanRepository travelPlanRepository;
    private final UserInfoService userInfoService;
    private final CheckerService checkerService;


    @Autowired
    TravelPlanMapper mapper;

    @Transactional
    public ResponseEntity<TravelPlanResponse> createTravelPlan(TravelPlanRequest travelPlanRequest) {
        Patient patient = userInfoService.getPatientFromAuth();
        Long patientId = patient.getId();
        Long appointmentId = travelPlanRequest.getAppointmentId();
        Long reservationId = travelPlanRequest.getHotelReservationId();
        Long flightId = travelPlanRequest.getFlightInformationId();

        Appointment appointment = checkerService.checkAppointmentById(appointmentId,patientId);
        HotelReservation hotelReservation = checkerService.checkHotelReservationById(reservationId,patientId);
        FlightInformation flight = checkerService.checkFlightById(flightId,patientId);

        if(Objects.isNull(appointment)) {
            throw new CheckIsNotSuccessException("Appointment with this appointment id not found or appointment id does not match with patient id.Check again.");
        }
        if(Objects.isNull(hotelReservation)) {
            throw new CheckIsNotSuccessException("HotelReservation with this reservation id not found or reservation id does not match with patient id.Check again.");
        }
        if(Objects.isNull(flight)) {
            throw new CheckIsNotSuccessException("Flight with this flight id not found or flight id does not match with patient id.Check again.");
        }

        TravelPlan travelPlan = mapper.requestToEntity(travelPlanRequest);
        travelPlan.setStatus(Status.APPROVED);
        travelPlan.setPatientId(patientId);
        travelPlan = travelPlanRepository.save(travelPlan);
        checkerService.setStatusToApproved(appointment,hotelReservation,flight);
        return new ResponseEntity<>(mapper.entityToResponse(travelPlan), HttpStatus.CREATED);
    }
}
