package com.vetCare.VetCare.application.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequestDto {
    private Long userId;
    private Long serviceId;
    private LocalDateTime appointmentDateTime;
    private String notes;
}
