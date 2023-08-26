package com.example.healthtourismapplication.model.responseDTO;

import com.example.healthtourismapplication.database.entity.Appointment;
import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.database.entity.FlightInformation;
import com.example.healthtourismapplication.database.entity.HotelReservation;
import lombok.Data;

import java.util.List;

@Data
public class PatientResponseDTO {
    private String nameSurname;
    private int age;
    private String gender;

    private List<Doctor> doctors;
    private List<Appointment> appointments;
    private List<HotelReservation> hotelReservations;
    private List<FlightInformation> flightInformations;
}
