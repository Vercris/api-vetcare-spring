package com.vetCare.VetCare.application.dto.response;

import com.vetCare.VetCare.domain.model.enums.AppointmentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponseDto {
    private Long id;
    private Long userId;
    private String userName;
    private Long serviceId;
    private String serviceName;
    private LocalDateTime appointmentDateTime;
    private AppointmentStatus status;
    private String notes;
    private LocalDateTime createdAt;
}
