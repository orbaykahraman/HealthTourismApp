package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Hospital extends BaseEntity {
    private String name;
    private String address;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;

}
