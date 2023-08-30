package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.model.enums.Status;
import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelReservation extends BaseEntity {

    private Date reservationDate;

    private Long patientId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
