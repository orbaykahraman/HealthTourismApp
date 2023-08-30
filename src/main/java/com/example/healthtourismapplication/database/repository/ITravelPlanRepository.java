package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.TravelPlan;
import com.example.healthtourismapplication.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITravelPlanRepository extends JpaRepository<TravelPlan,Long> {

    List<TravelPlan> findAllByStatus(Status status);
}
