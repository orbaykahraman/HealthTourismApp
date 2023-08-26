package com.example.healthtourismapplication.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userInfo")
@Builder
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    private String name;
    @NotNull
    private String nameSurname;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    private String roles;
    private int age;
    private String gender;
}
