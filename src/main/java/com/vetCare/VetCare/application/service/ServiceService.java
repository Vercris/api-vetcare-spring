package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.application.dto.request.ServiceRequestDto;
import com.vetCare.VetCare.application.dto.response.ServiceResponseDto;

import java.util.List;

public interface ServiceService {
    ServiceResponseDto create(ServiceRequestDto dto);
    ServiceResponseDto findById(Long id);
    List<ServiceResponseDto> findAll();
    ServiceResponseDto update(Long id, ServiceRequestDto dto);
    void delete(Long id);
}
