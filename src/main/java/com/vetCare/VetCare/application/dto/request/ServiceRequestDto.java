package com.vetCare.VetCare.application.dto.request;

import lombok.Data;

@Data
public class ServiceRequestDto {
    private Long categoryId;
    private String name;
    private String description;
    private Double price;
    private Integer durationMinutes;
    private Boolean isActive;
}
