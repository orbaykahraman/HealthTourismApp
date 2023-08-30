package com.example.healthtourismapplication.service;


import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.database.entity.Hospital;
import com.example.healthtourismapplication.database.repository.IDoctorRepository;
import com.example.healthtourismapplication.database.repository.IHospitalRepository;
import com.example.healthtourismapplication.exception.NotFoundWithIdException;
import com.example.healthtourismapplication.mapper.HospitalMapper;
import com.example.healthtourismapplication.model.requestDTO.HospitalRequest;
import com.example.healthtourismapplication.model.responseDTO.HospitalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final IHospitalRepository hospitalRepository;

    private final IDoctorRepository doctorRepository;

    @Autowired
    HospitalMapper mapper;


    public ResponseEntity<HospitalResponse> createHospital(HospitalRequest hospitalRequest) {
        Hospital hospital = hospitalRepository.save(mapper.hospitalRequestToHospital(hospitalRequest));
        return new ResponseEntity<>(mapper.hospitalTOHospitalResponse(hospital), HttpStatus.CREATED);
    }

    public ResponseEntity<HospitalResponse> getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(() -> new NotFoundWithIdException("Hospital not found with this id" + id));
        return new ResponseEntity<>(mapper.hospitalTOHospitalResponse(hospital), HttpStatus.FOUND);
    }

    public ResponseEntity<List<HospitalResponse>> findAllHospitals() {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        return new ResponseEntity<>(mapper.hospitalListTOHotelResponseList(hospitalList), HttpStatus.OK);
    }

    public void deleteHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElse(null);

        if(hospital != null) {
            List<Doctor> doctors = hospital.getDoctors();
            for(Doctor d : doctors) {
                d.setHospital(null);
                doctorRepository.save(d);
            }
            hospitalRepository.deleteById(id);
        }
    }

    public ResponseEntity<List<HospitalResponse>> findHospitalsByLocation(String location) {
        List<Hospital> hospitals = hospitalRepository.findByLocation(location);
        return new ResponseEntity<>(mapper.hospitalListTOHotelResponseList(hospitals),HttpStatus.OK);
    }
}
