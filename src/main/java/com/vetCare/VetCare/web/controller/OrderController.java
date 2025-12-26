package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.OrderRequestDto;
import com.vetCare.VetCare.application.dto.response.OrderResponseDto;
import com.vetCare.VetCare.application.service.OrderService;
import com.vetCare.VetCare.domain.model.Order;
import com.vetCare.VetCare.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody OrderRequestDto dto,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(orderService.createOrder(dto, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> findByUser(
            @PathVariable User userId) {

        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<OrderResponseDto>> getMyOrders(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(orderService.getOrdersByUser(user));
    }



}
