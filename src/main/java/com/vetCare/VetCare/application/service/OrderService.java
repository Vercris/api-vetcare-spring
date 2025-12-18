package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.domain.model.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order);

    List<Order> findByUser(Long userId);

    Order findById(Long id);
}
