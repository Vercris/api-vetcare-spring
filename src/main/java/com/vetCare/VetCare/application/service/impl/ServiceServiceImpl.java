package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.dto.request.ServiceRequestDto;
import com.vetCare.VetCare.application.dto.response.ServiceResponseDto;
import com.vetCare.VetCare.application.mapper.ServiceMapper;
import com.vetCare.VetCare.application.service.ServiceService;
import com.vetCare.VetCare.domain.model.Category;
import com.vetCare.VetCare.domain.model.ServiceEntity;
import com.vetCare.VetCare.domain.model.enums.CategoryType;
import com.vetCare.VetCare.domain.repository.CategoryRepository;
import com.vetCare.VetCare.domain.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public ServiceResponseDto create(ServiceRequestDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (category.getType() != CategoryType.SERVICE) {
            throw new RuntimeException("La categoría seleccionada no es válida para servicios");
        }

        ServiceEntity service = serviceMapper.toEntity(dto, category);
        service.setCreatedAt(LocalDateTime.now());
        service.setUpdatedAt(LocalDateTime.now());

        ServiceEntity saved = serviceRepository.save(service);
        return serviceMapper.toDto(saved);
    }

    @Override
    public ServiceResponseDto findById(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        return serviceMapper.toDto(service);
    }

    @Override
    public List<ServiceResponseDto> findAll() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponseDto update(Long id, ServiceRequestDto dto) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (category.getType() != CategoryType.SERVICE) {
            throw new RuntimeException("La categoría seleccionada no es válida para servicios");
        }

        service.setCategory(category);
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPrice(dto.getPrice());
        service.setDurationMinutes(dto.getDurationMinutes());
        service.setIsActive(dto.getIsActive());
        service.setUpdatedAt(LocalDateTime.now());

        ServiceEntity updated = serviceRepository.save(service);
        return serviceMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Servicio no encontrado");
        }
        serviceRepository.deleteById(id);
    }
}
