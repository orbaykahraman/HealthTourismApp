package com.example.healthtourismapplication.mapper;

import com.example.healthtourismapplication.database.entity.Doctor;
import com.example.healthtourismapplication.model.requestDTO.DoctorRequest;
import com.example.healthtourismapplication.model.responseDTO.DoctorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    Doctor doctorRequestToDoctor(DoctorRequest doctorRequest);

    DoctorResponse doctorToDoctorResponse(Doctor doctor);

    List<DoctorResponse> doctorListToDoctorResponseList(List<Doctor> doctorList);
}
