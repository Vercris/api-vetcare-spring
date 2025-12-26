package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.application.dto.request.OrderRequestDto;
import com.vetCare.VetCare.application.dto.response.OrderResponseDto;
import com.vetCare.VetCare.domain.model.Order;
import com.vetCare.VetCare.domain.model.User;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto dto, User user);

    OrderResponseDto findById(Long id);

    List<OrderResponseDto> getOrdersByUser(User user);

}

