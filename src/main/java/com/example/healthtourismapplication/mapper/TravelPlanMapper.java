package com.example.healthtourismapplication.mapper;

import com.example.healthtourismapplication.database.entity.TravelPlan;
import com.example.healthtourismapplication.model.requestDTO.TravelPlanRequest;
import com.example.healthtourismapplication.model.responseDTO.TravelPlanResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TravelPlanMapper {

    TravelPlanMapper INSTANCE = Mappers.getMapper(TravelPlanMapper.class);

    TravelPlan requestToEntity(TravelPlanRequest travelPlanRequest);

    TravelPlanResponse entityToResponse(TravelPlan travelPlan);

    List<TravelPlanResponse> entityListToResponseList(List<TravelPlan> travelPlanList);
}
