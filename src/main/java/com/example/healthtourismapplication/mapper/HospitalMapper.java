package com.example.healthtourismapplication.mapper;

import com.example.healthtourismapplication.database.entity.Hospital;
import com.example.healthtourismapplication.model.requestDTO.HospitalRequest;
import com.example.healthtourismapplication.model.responseDTO.HospitalResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HospitalMapper {
    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

    Hospital hospitalRequestToHospital(HospitalRequest hospitalRequest);

    HospitalResponse hospitalTOHospitalResponse(Hospital hospital);

    List<HospitalResponse> hospitalListTOHotelResponseList(List<Hospital> hospitalList);
}
