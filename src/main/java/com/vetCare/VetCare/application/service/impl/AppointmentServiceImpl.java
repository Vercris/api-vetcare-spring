package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.dto.request.AppointmentRequestDto;
import com.vetCare.VetCare.application.dto.response.AppointmentResponseDto;
import com.vetCare.VetCare.application.mapper.AppointmentMapper;
import com.vetCare.VetCare.application.service.AppointmentService;
import com.vetCare.VetCare.domain.model.Appointment;
import com.vetCare.VetCare.domain.model.ServiceEntity;
import com.vetCare.VetCare.domain.model.User;
import com.vetCare.VetCare.domain.model.enums.AppointmentStatus;
import com.vetCare.VetCare.domain.repository.AppointmentRepository;
import com.vetCare.VetCare.domain.repository.ServiceRepository;
import com.vetCare.VetCare.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        ServiceEntity service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setService(service);
        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
        appointment.setNotes(dto.getNotes());
        appointment.setStatus(AppointmentStatus.SCHEDULED); // Estado inicial
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());

        Appointment saved = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(saved);
    }

    @Override
    public AppointmentResponseDto findAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public List<AppointmentResponseDto> findAppointmentsByUser(Long userId) {
        return appointmentRepository.findByUserId(userId).stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDto updateAppointmentStatus(Long id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        
        appointment.setStatus(status);
        appointment.setUpdatedAt(LocalDateTime.now());

        Appointment updated = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(updated);
    }

    @Override
    public List<AppointmentResponseDto> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
