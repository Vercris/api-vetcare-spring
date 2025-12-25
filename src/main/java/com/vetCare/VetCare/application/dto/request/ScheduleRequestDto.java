package com.vetCare.VetCare.application.dto.request;

import lombok.Data;

@Data
public class ScheduleRequestDto {
    private Long serviceId;
    private Integer dayOfWeek;
    private String startTime;
    private String endTime;
    private Boolean isAvailable;
    private Integer maxCapacity;
}
