package com.example.healthtourismapplication.mapper;

import com.example.healthtourismapplication.database.entity.Hotel;
import com.example.healthtourismapplication.database.entity.Patient;
import com.example.healthtourismapplication.model.requestDTO.HotelRequest;
import com.example.healthtourismapplication.model.responseDTO.HotelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    Hotel requestToHotel(HotelRequest hotelRequest);

    HotelResponse hotelToHotelResponse (Hotel hotel);

    List<HotelResponse> hotelListToHotelResponse (List<Hotel> hotelList);

}
