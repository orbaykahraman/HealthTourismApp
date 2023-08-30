package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.Airplane;
import com.example.healthtourismapplication.database.repository.IAirplaneRepository;
import com.example.healthtourismapplication.exception.NotFoundWithIdException;
import com.example.healthtourismapplication.mapper.AirplaneMapper;
import com.example.healthtourismapplication.model.requestDTO.AirplaneRequest;
import com.example.healthtourismapplication.model.responseDTO.AirplaneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirplaneService {

    private final IAirplaneRepository airplaneRepository;
    @Autowired
    AirplaneMapper mapper;

    public ResponseEntity<AirplaneResponse> createAirPlane(AirplaneRequest airplaneRequest) {
        Airplane airplane = mapper.requestToAirplane(airplaneRequest);
        airplane.setRemainingSeats(airplaneRequest.getSeatNumber());
        airplane = airplaneRepository.save(airplane);
        return new ResponseEntity<>(mapper.airplaneToResponse(airplane), HttpStatus.CREATED);
    }

    public ResponseEntity<AirplaneResponse> findAirPlaneById(Long id) {
        Airplane airplane = airplaneRepository.findById(id).orElseThrow(() -> new NotFoundWithIdException("Airplane not found with this id" + id));
        return new ResponseEntity<>(mapper.airplaneToResponse(airplane), HttpStatus.FOUND);
    }

    public ResponseEntity<List<AirplaneResponse>> findAllAirPlanes() {
        List<Airplane> airplaneList = airplaneRepository.findAll();
        return new ResponseEntity<>(mapper.airplaneListToAirplaneResponse(airplaneList), HttpStatus.OK);
    }

    public void deleteAirPlaneById(Long id) {
        airplaneRepository.deleteById(id);
    }

    public ResponseEntity<List<AirplaneResponse>> findAirplanesByDestination(String destination) {
        List<Airplane> airplaneList = airplaneRepository.findAirplanesByDestination(destination);
        return new ResponseEntity<>(mapper.airplaneListToAirplaneResponse(airplaneList),HttpStatus.OK);
    }
}
