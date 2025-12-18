package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.service.OrderService;
import com.vetCare.VetCare.domain.model.Order;
import com.vetCare.VetCare.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findByUser(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }
}
