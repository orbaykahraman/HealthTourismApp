package com.example.healthtourismapplication.database.entity;

import com.example.healthtourismapplication.util.BaseEntity;
import com.example.healthtourismapplication.util.BaseEntityWithoutAutoId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseEntityWithoutAutoId {
    private String nameSurname;
    private int age;
    private String gender;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "patients")
    private List<Doctor> doctors;
}
