package com.example.healthtourismapplication.mapper;

import com.example.healthtourismapplication.database.entity.Airplane;
import com.example.healthtourismapplication.model.requestDTO.AirplaneRequest;
import com.example.healthtourismapplication.model.responseDTO.AirplaneResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirplaneMapper {

    AirplaneMapper INSTANCE = Mappers.getMapper(AirplaneMapper.class);

    Airplane requestToAirplane (AirplaneRequest airplaneRequest);

    AirplaneResponse airplaneToResponse (Airplane airplane);

    List<AirplaneResponse> airplaneListToAirplaneResponse(List<Airplane> airplaneList);
}
