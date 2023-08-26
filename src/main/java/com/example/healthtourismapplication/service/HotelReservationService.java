package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.repository.IHotelReservationRepository;
import com.example.healthtourismapplication.model.requestDTO.HotelReservationRequest;
import com.example.healthtourismapplication.model.responseDTO.HotelReservationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelReservationService {
    private final IHotelReservationRepository hotelReservationRepository;


    public ResponseEntity<HotelReservationResponseDTO> save(HotelReservationRequest hotelReservationRequest) {
        return null;
    }
}
