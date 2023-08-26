package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelRepository extends JpaRepository<Hotel, Long> {
}
