package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.database.entity.Hospital;
import com.example.healthtourismapplication.database.entity.UserInfo;
import com.example.healthtourismapplication.database.repository.IDoctorRepository;
import com.example.healthtourismapplication.database.repository.IHospitalRepository;
import com.example.healthtourismapplication.exception.NotFoundWithIdException;
import com.example.healthtourismapplication.mapper.DoctorMapper;
import com.example.healthtourismapplication.model.requestDTO.DoctorRequest;
import com.example.healthtourismapplication.model.responseDTO.DoctorResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final IDoctorRepository doctorRepository;

    private final IHospitalRepository hospitalRepository;

    @Autowired
    DoctorMapper mapper;

    public void createDoctorWhileRegister(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public ResponseEntity<DoctorResponse> getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new NotFoundWithIdException("Doctor not found with id : " + id));

        return new ResponseEntity<>(mapper.doctorToDoctorResponse(doctor), HttpStatus.FOUND);
    }

    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return new ResponseEntity<>(mapper.doctorListToDoctorResponseList(doctors),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<DoctorResponse> createDoctor(DoctorRequest doctorRequest) {
        Long hospitalId = doctorRequest.getHospitalId();
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new NotFoundWithIdException("Hospital not found with id : " + hospitalId));
        Doctor doctor = mapper.doctorRequestToDoctor(doctorRequest);
        doctor.setHospital(hospital);
        List<Doctor> doctorListOfHospital = hospital.getDoctors();
        doctorListOfHospital.add(doctor);
        hospital.setDoctors(doctorListOfHospital);
        doctor = doctorRepository.save(doctor);
        hospitalRepository.save(hospital);
        return new ResponseEntity<>(mapper.doctorToDoctorResponse(doctor),HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<DoctorResponse> addHospitalToDoctor(Long hospitalId, Long doctorId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new NotFoundWithIdException("Hospital not found with id : " + hospitalId));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundWithIdException("Doctor not found with id : " + doctorId));
        doctor.setHospital(hospital);
        List<Doctor> doctorListOfHospital = hospital.getDoctors();
        doctorListOfHospital.add(doctor);
        hospital.setDoctors(doctorListOfHospital);
        doctor = doctorRepository.save(doctor);
        hospitalRepository.save(hospital);
        return new ResponseEntity<>(mapper.doctorToDoctorResponse(doctor),HttpStatus.CREATED);
    }

    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor getDoctorFromAuth(UserInfo userInfo) {
        Long doctorId = userInfo.getUserId();
        return doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundWithIdException("Doctor not found with id : " + doctorId));
    }

    public ResponseEntity<List<DoctorResponse>> findDoctorsByHospitalLocationAndDepartment(String location, String department) {
        List<Hospital> hospitals = hospitalRepository.findByLocation(location);
        List<Doctor> doctorsToReturn = new ArrayList<>();

        for(Hospital h:hospitals) {
            List<Doctor> doctors = h.getDoctors();
            for(Doctor doctor : doctors) {
                if(department.equals(doctor.getDepartment())) {
                    doctorsToReturn.add(doctor);
                }
            }
        }
        return new ResponseEntity<>(mapper.doctorListToDoctorResponseList(doctorsToReturn),HttpStatus.OK);
    }
}
