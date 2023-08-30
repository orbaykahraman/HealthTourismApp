package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.Patient;
import com.example.healthtourismapplication.database.entity.UserInfo;
import com.example.healthtourismapplication.database.repository.IPatientRepository;
import com.example.healthtourismapplication.exception.NotFoundWithIdException;
import com.example.healthtourismapplication.mapper.PatientMapper;
import com.example.healthtourismapplication.model.requestDTO.PatientRequestDTO;
import com.example.healthtourismapplication.model.responseDTO.PatientResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final IPatientRepository patientRepository;

    @Autowired
    PatientMapper mapper;

    public ResponseEntity<PatientResponseDTO> createPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = mapper.requestDtoToPatient(patientRequestDTO);
        patient = patientRepository.save(patient);
        return new ResponseEntity<>(mapper.patientToResponseDto(patient), HttpStatus.CREATED);
    }

    public ResponseEntity<PatientResponseDTO> getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new NotFoundWithIdException("Patient not found with id : " + id));
        return new ResponseEntity<>(mapper.patientToResponseDto(patient),HttpStatus.FOUND);
    }

    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return new ResponseEntity<>(mapper.patientListToResponeseDtoList(patients),HttpStatus.OK);
    }

    public void createPatientWhileRegister(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getPatientFromAuth(UserInfo userInfo) {
        Long patientId = userInfo.getUserId();
        return patientRepository.findById(patientId).orElseThrow(() -> new NotFoundWithIdException("Patient not found with id : " + patientId));
    }

    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }
}
