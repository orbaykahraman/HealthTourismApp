package com.example.healthtourismapplication.mapper;

import com.example.healthtourismapplication.database.entity.HotelReservation;
import com.example.healthtourismapplication.model.requestDTO.HotelReservationRequest;
import com.example.healthtourismapplication.model.responseDTO.HotelReservationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HotelReservationMapper {

    HotelReservationMapper INSTANCE = Mappers.getMapper(HotelReservationMapper.class);

    HotelReservation requestToEntity(HotelReservationRequest hotelReservationRequest);

    HotelReservationResponseDTO entityToResponse(HotelReservation hotelReservation);

}
