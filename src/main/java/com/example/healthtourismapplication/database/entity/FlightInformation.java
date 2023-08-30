package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.model.enums.Status;
import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class FlightInformation extends BaseEntity {
    private String name;
    private Date flightDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
}
