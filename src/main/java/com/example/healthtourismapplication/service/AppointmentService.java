package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.Appointment;
import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.database.entity.Patient;
import com.example.healthtourismapplication.database.entity.UserInfo;
import com.example.healthtourismapplication.database.repository.IAppointmentRepository;
import com.example.healthtourismapplication.database.repository.IDoctorRepository;
import com.example.healthtourismapplication.database.repository.IPatientRepository;
import com.example.healthtourismapplication.database.repository.UserInfoRepository;
import com.example.healthtourismapplication.exception.AppointmentNotFinishedException;
import com.example.healthtourismapplication.exception.InvalidDateException;
import com.example.healthtourismapplication.exception.NotFoundWithIdException;
import com.example.healthtourismapplication.mapper.AppointmentMapper;
import com.example.healthtourismapplication.model.enums.Status;
import com.example.healthtourismapplication.model.requestDTO.AppointmentRequest;
import com.example.healthtourismapplication.model.responseDTO.AppointmentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final IAppointmentRepository appointmentRepository;

    private final IDoctorRepository doctorRepository;

    private final IPatientRepository patientRepository;

    private final UserInfoService userInfoService;

    @Autowired
    private AppointmentMapper mapper;

    public ResponseEntity<AppointmentResponse> getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new NotFoundWithIdException("Appointment not found with this id : " + id));
        return new ResponseEntity<>(mapper.appointmentToResponse(appointment), HttpStatus.FOUND);
    }

    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return new ResponseEntity<>(mapper.appointmentListToResponseList(appointments),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<AppointmentResponse> createAppointment(AppointmentRequest appointmentRequest) {
        Patient patient = userInfoService.getPatientFromAuth();
        Long patientId = patient.getId();
        Long doctorId = appointmentRequest.getDoctorId();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundWithIdException("Doctor not found with this id : " + doctorId));
        Appointment appointment = mapper.requestToAppointment(appointmentRequest);

        if(appointment.getAppointmentDate().before(new Date())) {
            throw new InvalidDateException("Appointment Date can not before today.");
        }

        appointment.setStatus(Status.WAITING);
        appointment.setDoctor(doctor);
        appointment.setPatientId(patientId);

        List<Appointment> appointments = doctor.getAppointments();
        appointments.add(appointment);
        doctor.setAppointments(appointments);

        List<Doctor> doctors = patient.getDoctors();
        doctors.add(doctor);
        patient.setDoctors(doctors);

        appointment = appointmentRepository.save(appointment);
        doctorRepository.save(doctor);
        patientRepository.save(patient);
        return new ResponseEntity<>(mapper.appointmentToResponse(appointment),HttpStatus.CREATED);
    }

    public ResponseEntity<List<AppointmentResponse>> getMyAppointments() {
        Long patientId = userInfoService.getPatientFromAuth().getId();
        return new ResponseEntity<>(mapper.appointmentListToResponseList(appointmentRepository.findByPatientId(patientId)),HttpStatus.OK);
    }

    public void deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
    }

    public ResponseEntity<AppointmentResponse> setAppointmentNotesById(Long id, String appointmentNotes) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new NotFoundWithIdException("Appointment not found with this id : " + id));
        if(!appointment.getStatus().equals(Status.FINISHED)) {
            throw new AppointmentNotFinishedException("Appointment status should be 'finished' before setting appointment notes.");
        }
        appointment.setAppointmentNotes(appointmentNotes);
        appointment = appointmentRepository.save(appointment);
        return new ResponseEntity<>(mapper.appointmentToResponse(appointment),HttpStatus.OK);
    }

    public ResponseEntity<List<AppointmentResponse>> getAppointmentsOfDoctor() {
        Doctor doctor = userInfoService.getDoctorFromAuth();
        return new ResponseEntity<>(mapper.appointmentListToResponseList(appointmentRepository.findByDoctor(doctor)),HttpStatus.OK);
    }
}
