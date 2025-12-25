package com.vetCare.VetCare.application.mapper;

import com.vetCare.VetCare.application.dto.request.ServiceRequestDto;
import com.vetCare.VetCare.application.dto.response.ServiceResponseDto;
import com.vetCare.VetCare.domain.model.Category;
import com.vetCare.VetCare.domain.model.ServiceEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public ServiceEntity toEntity(ServiceRequestDto dto, Category category) {
        ServiceEntity service = new ServiceEntity();
        service.setCategory(category);
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPrice(dto.getPrice());
        service.setDurationMinutes(dto.getDurationMinutes());
        service.setIsActive(dto.getIsActive());
        return service;
    }

    public ServiceResponseDto toDto(ServiceEntity service) {
        ServiceResponseDto dto = new ServiceResponseDto();
        dto.setId(service.getId());
        if (service.getCategory() != null) {
            dto.setCategoryId(service.getCategory().getId());
            dto.setCategoryName(service.getCategory().getName());
        }
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setPrice(service.getPrice());
        dto.setDurationMinutes(service.getDurationMinutes());
        dto.setIsActive(service.getIsActive());
        dto.setCreatedAt(service.getCreatedAt());
        dto.setUpdatedAt(service.getUpdatedAt());
        return dto;
    }
}
