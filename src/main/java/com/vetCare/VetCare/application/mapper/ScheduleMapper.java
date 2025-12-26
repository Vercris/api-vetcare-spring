package com.vetCare.VetCare.application.mapper;

import com.vetCare.VetCare.application.dto.request.ScheduleRequestDto;
import com.vetCare.VetCare.application.dto.response.ScheduleResponseDto;
import com.vetCare.VetCare.domain.model.Schedule;
import com.vetCare.VetCare.domain.model.ServiceEntity;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    public Schedule toEntity(ScheduleRequestDto dto, ServiceEntity service) {
        Schedule schedule = new Schedule();
        schedule.setService(service);
        schedule.setDayOfWeek(dto.getDayOfWeek());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setIsAvailable(dto.getIsAvailable());
        schedule.setMaxCapacity(dto.getMaxCapacity());
        return schedule;
    }

    public ScheduleResponseDto toDto(Schedule schedule) {
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(schedule.getId());
        if (schedule.getService() != null) {
            dto.setServiceId(schedule.getService().getId());
            dto.setServiceName(schedule.getService().getName());
        }
        dto.setDayOfWeek(schedule.getDayOfWeek());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        dto.setIsAvailable(schedule.getIsAvailable());
        dto.setMaxCapacity(schedule.getMaxCapacity());
        return dto;
    }
}
