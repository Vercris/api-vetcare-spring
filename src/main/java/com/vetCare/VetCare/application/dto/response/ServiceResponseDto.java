package com.vetCare.VetCare.application.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ServiceResponseDto {
    private Long id;
    private Long categoryId;
    private String categoryName;
    private String name;
    private String description;
    private Double price;
    private Integer durationMinutes;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
