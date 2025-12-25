package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.dto.request.ScheduleRequestDto;
import com.vetCare.VetCare.application.dto.response.ScheduleResponseDto;
import com.vetCare.VetCare.application.mapper.ScheduleMapper;
import com.vetCare.VetCare.application.service.ScheduleService;
import com.vetCare.VetCare.domain.model.Schedule;
import com.vetCare.VetCare.domain.model.ServiceEntity;
import com.vetCare.VetCare.domain.repository.ScheduleRepository;
import com.vetCare.VetCare.domain.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ServiceRepository serviceRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        ServiceEntity service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        Schedule schedule = scheduleMapper.toEntity(dto, service);
        Schedule saved = scheduleRepository.save(schedule);
        return scheduleMapper.toDto(saved);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        ServiceEntity service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        schedule.setService(service);
        schedule.setDayOfWeek(dto.getDayOfWeek());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setIsAvailable(dto.getIsAvailable());
        schedule.setMaxCapacity(dto.getMaxCapacity());

        Schedule updated = scheduleRepository.save(schedule);
        return scheduleMapper.toDto(updated);
    }

    @Override
    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Horario no encontrado");
        }
        scheduleRepository.deleteById(id);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        return scheduleMapper.toDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByService(Long serviceId) {
        return scheduleRepository.findByServiceId(serviceId).stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleResponseDto> findAvailableSchedules() {
        return scheduleRepository.findByIsAvailableTrue().stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }
}
