package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.util.BaseEntity;
import com.example.healthtourismapplication.util.BaseEntityWithoutAutoId;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Doctor  extends BaseEntityWithoutAutoId {

    private String nameSurname;

    @ManyToMany
    private List<Patient> patients;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;



}
