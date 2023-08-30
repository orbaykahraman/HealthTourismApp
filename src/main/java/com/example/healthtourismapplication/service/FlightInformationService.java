package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.Airplane;
import com.example.healthtourismapplication.database.entity.FlightInformation;
import com.example.healthtourismapplication.database.entity.Patient;
import com.example.healthtourismapplication.database.repository.IAirplaneRepository;
import com.example.healthtourismapplication.database.repository.IFlightInformationRepository;
import com.example.healthtourismapplication.exception.InvalidDateException;
import com.example.healthtourismapplication.exception.NoAppointmentFoundException;
import com.example.healthtourismapplication.exception.NoRemainingSeatsException;
import com.example.healthtourismapplication.exception.NotFoundWithIdException;
import com.example.healthtourismapplication.mapper.FlightInformationMapper;
import com.example.healthtourismapplication.model.enums.Status;
import com.example.healthtourismapplication.model.requestDTO.FlightInformationRequest;
import com.example.healthtourismapplication.model.responseDTO.FlightInformationResponse;
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
public class FlightInformationService {
    private final IFlightInformationRepository flightInformationRepository;
    private final IAirplaneRepository airplaneRepository;
    private final CheckerService checkerService;
    private final UserInfoService userInfoService;

    @Autowired
    FlightInformationMapper mapper;

    @Transactional
    public ResponseEntity<FlightInformationResponse> createFlight(FlightInformationRequest flightInformationRequest) {
        FlightInformation flightInformation = mapper.requestToEntity(flightInformationRequest);
        Airplane airplane = airplaneRepository.findById(flightInformationRequest.getAirplaneId()).orElseThrow(() -> new NotFoundWithIdException("Airplane not found"));
        Patient patient = userInfoService.getPatientFromAuth();

        //ucak biletinden önce appointment ve hotelreservation yapilmis olmali.
        if(flightInformation.getFlightDate().before(new Date())) {
            throw new InvalidDateException("Flight Date can not before today.");
        }
        if(!checkerService.checkAppointment(patient)) {
            throw new NoAppointmentFoundException("You must make appointment before flight.");
        }
        if(!checkerService.checkHotelReservation(patient)) {
            throw new NoAppointmentFoundException("You must make hotel reservation before flight.");
        }
        if(airplane.getRemainingSeats() == 0) {
            throw new NoRemainingSeatsException("There are no remaining seats in this airplane.You can not buy ticket.");
        }
        airplane.setRemainingSeats(airplane.getRemainingSeats() - 1);
        flightInformation.setStatus(Status.WAITING);
        flightInformation.setAirplane(airplane);
        flightInformation.setPatientId(patient.getId());
        if(flightInformation.getFlightDate() == null) {
            flightInformation.setFlightDate(airplane.getFlightDate());
        }

        List<FlightInformation> flightInformationList = airplane.getFlightInformations();
        flightInformationList.add(flightInformation);
        airplane.setFlightInformations(flightInformationList);

        flightInformation = flightInformationRepository.save(flightInformation);
        airplaneRepository.save(airplane);
        return new ResponseEntity<>(mapper.entityToResponse(flightInformation), HttpStatus.CREATED);
    }

    public void deleteById(Long id) {
        FlightInformation flightInformation = flightInformationRepository.findById(id).orElse(null);
        if(flightInformation != null) {
            Airplane airplane = flightInformation.getAirplane();
            airplane.setRemainingSeats(airplane.getRemainingSeats() + 1);
            List<FlightInformation> flightInformationList = airplane.getFlightInformations();
            flightInformationList.remove(flightInformation);
            airplane.setFlightInformations(flightInformationList);
            airplaneRepository.save(airplane);
            flightInformationRepository.deleteById(id);
        }
    }

    public List<FlightInformation> getAllFlightsByStatus() {
        return flightInformationRepository.findAllByStatus(Status.WAITING);
    }
}
