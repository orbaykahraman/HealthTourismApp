package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Airplane extends BaseEntity {
    private String name;
    private Long seatNumber;
    private Long remainingSeats;
    private Date flightDate;
    private String destination;

    @OneToMany(mappedBy = "airplane")
    private List<FlightInformation> flightInformations;

}
