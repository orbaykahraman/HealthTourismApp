package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class HotelReservation extends BaseEntity {

    private Date reservationDate;

@ManyToOne
@JoinColumn(name = "patient_id")
private Patient patient;

@ManyToOne
@JoinColumn(name = "hotel_id")
private  Hotel hotel;
}
