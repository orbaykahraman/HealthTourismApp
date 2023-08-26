package com.example.healthtourismapplication.mapper;

import com.example.healthtourismapplication.database.entity.*;
import com.example.healthtourismapplication.model.requestDTO.PatientRequestDTO;
import com.example.healthtourismapplication.model.responseDTO.PatientResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);
    Patient requestDtoToPatient(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO patientToResponseDto(Patient patient);
    List<PatientResponseDTO> patientListToResponeseDtoList(List<Patient> patients);

}
