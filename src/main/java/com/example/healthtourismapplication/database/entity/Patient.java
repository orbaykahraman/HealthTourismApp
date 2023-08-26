package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Patient extends BaseEntity {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    @ManyToMany
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient")
    private List<HotelReservation> hotelReservations;

    @OneToMany(mappedBy = "patient")
    private List<FlightInformation> flightInformations;

}
