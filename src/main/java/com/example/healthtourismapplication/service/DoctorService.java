package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.database.repository.IDoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final IDoctorRepository doctorRepository;

    public void createPatientWhileRegister(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}
