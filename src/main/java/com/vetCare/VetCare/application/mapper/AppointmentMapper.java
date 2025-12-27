package com.vetCare.VetCare.application.mapper;

import com.vetCare.VetCare.application.dto.response.AppointmentResponseDto;
import com.vetCare.VetCare.domain.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentResponseDto toDto(Appointment appointment) {
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setId(appointment.getId());
        
        if (appointment.getUser() != null) {
            dto.setUserId(appointment.getUser().getId());
            dto.setUserName(appointment.getUser().getName());
        }

        if (appointment.getService() != null) {
            dto.setServiceId(appointment.getService().getId());
            dto.setServiceName(appointment.getService().getName());
        }

        dto.setAppointmentDateTime(appointment.getAppointmentDateTime());
        dto.setStatus(appointment.getStatus());
        dto.setNotes(appointment.getNotes());
        dto.setCreatedAt(appointment.getCreatedAt());

        return dto;
    }
}
