package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.dto.request.OrderRequestDto;
import com.vetCare.VetCare.application.dto.response.OrderResponseDto;
import com.vetCare.VetCare.application.mapper.OrderMapper;
import com.vetCare.VetCare.application.service.OrderService;
import com.vetCare.VetCare.domain.model.Order;
import com.vetCare.VetCare.domain.model.OrderItem;
import com.vetCare.VetCare.domain.model.Product;
import com.vetCare.VetCare.domain.model.User;
import com.vetCare.VetCare.domain.model.enums.OrderStatus;
import com.vetCare.VetCare.domain.repository.OrderRepository;
import com.vetCare.VetCare.domain.repository.ProductRepository;
import com.vetCare.VetCare.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDto create(OrderRequestDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Order order = orderMapper.toEntity(dto, user);

        BigDecimal subtotal = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            item.setUnitPrice(product.getPrice());
            item.setSubtotal(
                    product.getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()))
            );

            subtotal = subtotal.add(item.getSubtotal());
        }

        order.setSubtotal(subtotal);
        order.setTax(subtotal.multiply(BigDecimal.valueOf(0.18)));
        order.setShippingCost(BigDecimal.ZERO);
        order.setTotal(order.getSubtotal().add(order.getTax()));

        Order saved = orderRepository.save(order);

        return orderMapper.toDto(saved);
    }

    @Override
    public List<OrderResponseDto> findByUser(Long userId) {

        return orderRepository.findByUser_Id(userId)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto findById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        Order updated = orderRepository.save(order);
        return orderMapper.toDto(updated);
    }
}
