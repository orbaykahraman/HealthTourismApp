package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.database.entity.Patient;
import com.example.healthtourismapplication.database.entity.UserInfo;
import com.example.healthtourismapplication.database.repository.UserInfoRepository;
import com.example.healthtourismapplication.exception.NotFoundWithIdException;
import com.example.healthtourismapplication.exception.UserNameOrEmailAlreadyExistException;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity<String> register(UserInfo userInfo) {

        logger.info("add user started");

        String username = userInfo.getName();

        if(userInfoRepository.getUserInfoByName(username).isPresent() || userInfoRepository.findByEmail(userInfo.getEmail()).isPresent()) {
            throw new UserNameOrEmailAlreadyExistException("User or email already exists");
        }


        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        if(StringUtils.isEmpty(userInfo.getRoles())) {
            userInfo.setRoles("ROLE_PATIENT");
        }
        UserInfo savedUser = userInfoRepository.save(userInfo);
        logger.info("add user ended");

        if(userInfo.getRoles().equals("ROLE_PATIENT")) {
            Patient patient = new Patient();
            patient.setAge(savedUser.getAge());
            patient.setNameSurname(savedUser.getNameSurname());
            patient.setGender(savedUser.getGender());
            patient.setId(savedUser.getUserId());
            patientService.createPatientWhileRegister(patient);
            logger.info("Patient created");
            return new ResponseEntity<>(String.valueOf(patient.getId()), HttpStatus.CREATED);
        }
        else if(userInfo.getRoles().equals("ROLE_DOCTOR")) {
            Doctor doctor = new Doctor();
            doctor.setNameSurname(savedUser.getNameSurname());
            doctor.setId(savedUser.getUserId());
            doctorService.createDoctorWhileRegister(doctor);
            logger.info("Doctor created");
            return new ResponseEntity<>(String.valueOf(doctor.getId()), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    public UserInfo getUserInfoFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        return userInfoRepository.findByName(username).orElseThrow(() -> new NotFoundWithIdException("User not found."));
    }
    public Patient getPatientFromAuth() {
        UserInfo userInfo = getUserInfoFromAuth();
        return patientService.getPatientFromAuth(userInfo);
    }

    public Doctor getDoctorFromAuth() {
        UserInfo userInfo = getUserInfoFromAuth();
        return doctorService.getDoctorFromAuth(userInfo);
    }
}
