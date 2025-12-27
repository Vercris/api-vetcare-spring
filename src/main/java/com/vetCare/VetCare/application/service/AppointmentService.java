package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.application.dto.request.AppointmentRequestDto;
import com.vetCare.VetCare.application.dto.response.AppointmentResponseDto;
import com.vetCare.VetCare.domain.model.enums.AppointmentStatus;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDto createAppointment(AppointmentRequestDto dto);
    AppointmentResponseDto findAppointmentById(Long id);
    List<AppointmentResponseDto> findAppointmentsByUser(Long userId);
    AppointmentResponseDto updateAppointmentStatus(Long id, AppointmentStatus status);
    List<AppointmentResponseDto> findAll();
}
