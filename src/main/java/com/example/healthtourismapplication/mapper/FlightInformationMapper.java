package com.example.healthtourismapplication.mapper;

import com.example.healthtourismapplication.database.entity.FlightInformation;
import com.example.healthtourismapplication.model.requestDTO.FlightInformationRequest;
import com.example.healthtourismapplication.model.responseDTO.FlightInformationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightInformationMapper {

    FlightInformationMapper INSTANCE = Mappers.getMapper(FlightInformationMapper.class);

    FlightInformation requestToEntity(FlightInformationRequest flightInformationRequest);

    FlightInformationResponse entityToResponse(FlightInformation flightInformation);

    List<FlightInformationResponse> entityListToResponseList(List<FlightInformation> flightInformations);
}
