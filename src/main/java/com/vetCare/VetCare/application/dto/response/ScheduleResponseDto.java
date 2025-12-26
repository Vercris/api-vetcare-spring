package com.vetCare.VetCare.application.dto.response;

import lombok.Data;

@Data
public class ScheduleResponseDto {
    private Long id;
    private Long serviceId;
    private String serviceName;
    private Integer dayOfWeek;
    private String startTime;
    private String endTime;
    private Boolean isAvailable;
    private Integer maxCapacity;
}
