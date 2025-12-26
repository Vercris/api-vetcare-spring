package com.vetCare.VetCare.application.mapper;

import com.vetCare.VetCare.application.dto.request.OrderItemRequestDto;
import com.vetCare.VetCare.application.dto.request.OrderRequestDto;
import com.vetCare.VetCare.application.dto.response.OrderItemResponseDto;
import com.vetCare.VetCare.application.dto.response.OrderResponseDto;
import com.vetCare.VetCare.domain.model.Order;
import com.vetCare.VetCare.domain.model.OrderItem;
import com.vetCare.VetCare.domain.model.Product;
import com.vetCare.VetCare.domain.model.User;
import com.vetCare.VetCare.domain.model.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    /* ===============================
       DTO -> ENTITY
       =============================== */

    public Order toEntity(OrderRequestDto dto, User user) {

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderNumber("ORD-" + System.currentTimeMillis());
        order.setAddress(dto.getAddress()); // 👈 OBLIGATORIO

        List<OrderItem> items = dto.getItems()
                .stream()
                .map(this::toOrderItemEntity)
                .collect(Collectors.toList());

        items.forEach(item -> item.setOrder(order));
        order.setItems(items);

        return order;
    }



    private OrderItem toOrderItemEntity(OrderItemRequestDto dto) {

        OrderItem item = new OrderItem();
        item.setQuantity(dto.getQuantity());

        Product product = new Product();
        product.setId(dto.getProductId());
        item.setProduct(product);

        return item;
    }

    /* ===============================
       ENTITY -> DTO
       =============================== */

    public OrderResponseDto toDto(Order order) {

        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setStatus(order.getStatus());
        dto.setTotal(order.getTotal());

        List<OrderItemResponseDto> items = order.getItems()
                .stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());

        dto.setItems(items);
        return dto;
    }

    private OrderItemResponseDto toOrderItemDto(OrderItem item) {

        OrderItemResponseDto dto = new OrderItemResponseDto();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setSubtotal(item.getSubtotal());

        return dto;
    }
}

