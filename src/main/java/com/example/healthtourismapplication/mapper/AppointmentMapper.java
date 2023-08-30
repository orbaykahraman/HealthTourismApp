package com.example.healthtourismapplication.mapper;

import com.example.healthtourismapplication.database.entity.Appointment;
import com.example.healthtourismapplication.model.requestDTO.AppointmentRequest;
import com.example.healthtourismapplication.model.responseDTO.AppointmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    Appointment requestToAppointment(AppointmentRequest appointmentRequest);

    @Mapping(source = "doctor",target = "doctor")
    AppointmentResponse appointmentToResponse(Appointment appointment);

    List<AppointmentResponse> appointmentListToResponseList(List<Appointment> appointments);
}
