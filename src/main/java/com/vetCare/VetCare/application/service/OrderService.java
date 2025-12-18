package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.application.dto.request.OrderRequestDto;
import com.vetCare.VetCare.application.dto.response.OrderResponseDto;
import com.vetCare.VetCare.domain.model.Order;

import java.util.List;

public interface OrderService {
    OrderResponseDto create(OrderRequestDto dto);

    List<OrderResponseDto> findByUser(Long userId);

    OrderResponseDto findById(Long id);
}
