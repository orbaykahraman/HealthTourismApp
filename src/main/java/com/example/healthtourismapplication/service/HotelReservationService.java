package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.Hotel;
import com.example.healthtourismapplication.database.entity.HotelReservation;
import com.example.healthtourismapplication.database.entity.Patient;
import com.example.healthtourismapplication.database.repository.IHotelRepository;
import com.example.healthtourismapplication.database.repository.IHotelReservationRepository;
import com.example.healthtourismapplication.exception.InvalidDateException;
import com.example.healthtourismapplication.exception.NoAppointmentFoundException;
import com.example.healthtourismapplication.exception.NotFoundWithIdException;
import com.example.healthtourismapplication.mapper.HotelReservationMapper;
import com.example.healthtourismapplication.model.enums.Status;
import com.example.healthtourismapplication.model.requestDTO.HotelReservationRequest;
import com.example.healthtourismapplication.model.responseDTO.HotelReservationResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelReservationService {
    private final IHotelReservationRepository hotelReservationRepository;

    private final IHotelRepository hotelRepository;

    private final UserInfoService userInfoService;
    private final CheckerService checkerService;

    @Autowired
    HotelReservationMapper mapper;


    @Transactional
    public ResponseEntity<HotelReservationResponseDTO> createHotelReservation(HotelReservationRequest hotelReservationRequest) {
        HotelReservation hotelReservation = mapper.requestToEntity(hotelReservationRequest);
        Hotel hotel = hotelRepository.findById(hotelReservationRequest.getHotelId()).orElseThrow(() -> new NotFoundWithIdException("Hotel not found with id : " + hotelReservationRequest.getHotelId()));
        Patient patient = userInfoService.getPatientFromAuth();

        if(hotelReservation.getReservationDate().before(new Date())) {
            throw new InvalidDateException("Reservation date can not before today.");
        }

        //patient'in appointment'i olması şart
        if(!checkerService.checkAppointment(patient)) {
            throw new NoAppointmentFoundException("You must make appointment before hotel reservation.");
        }
        //hotel location ile hospital location aynı olmalı.
//        if(!checkerService.checkLocations(hotel.getLocation())) {
//            throw new LocationsDoesNotMatchException("Hotel and hospital locations are different.");
//        }
        hotelReservation.setStatus(Status.WAITING);
        hotelReservation.setPatientId(patient.getId());
        hotelReservation.setHotel(hotel);


        List<HotelReservation> hotelReservationList = hotel.getHotelReservations();
        hotelReservationList.add(hotelReservation);
        hotel.setHotelReservations(hotelReservationList);

        hotelReservationRepository.save(hotelReservation);
        hotelRepository.save(hotel);
        return new ResponseEntity<>(mapper.entityToResponse(hotelReservation), HttpStatus.CREATED);
    }

    public void deleteHotelReservationById(Long id) {
        hotelReservationRepository.deleteById(id);
    }


    public boolean isHotelReservationExistsByPatientId(Long patientId) {
        return hotelReservationRepository.existsHotelReservationByPatientIdAndStatusOrderByCreationDateDesc(patientId,Status.WAITING);
    }

    public List<HotelReservation> getAllReservationsByStatus() {
        return hotelReservationRepository.findAllByStatus(Status.WAITING);
    }
}
