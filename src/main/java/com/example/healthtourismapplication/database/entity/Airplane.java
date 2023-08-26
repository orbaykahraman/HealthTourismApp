package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Airplane extends BaseEntity {
    private String name;
    private Long seatNumber;

    @OneToMany(mappedBy = "airplane")
    private List<FlightInformation> flightInformations;

}
