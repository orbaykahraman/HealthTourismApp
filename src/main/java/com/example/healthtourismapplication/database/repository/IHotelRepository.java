package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocation(String location);
}
