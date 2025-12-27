package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.application.dto.request.ScheduleRequestDto;
import com.vetCare.VetCare.application.dto.response.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto);
    void deleteSchedule(Long id);
    ScheduleResponseDto findScheduleById(Long id);
    List<ScheduleResponseDto> findSchedulesByService(Long serviceId);
    List<ScheduleResponseDto> findAvailableSchedules();
}
