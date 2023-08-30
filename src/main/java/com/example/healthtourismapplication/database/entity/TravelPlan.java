package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.model.enums.Status;
import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelPlan extends BaseEntity {

    private Long patientId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private HotelReservation hotelReservation;

    @OneToOne
    @JoinColumn(name = "flight_id")
    private FlightInformation flightInformation;
}
