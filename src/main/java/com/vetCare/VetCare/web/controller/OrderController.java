package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.OrderRequestDto;
import com.vetCare.VetCare.application.dto.request.UpdateOrderStatusRequestDto;
import com.vetCare.VetCare.application.dto.response.OrderResponseDto;
import com.vetCare.VetCare.application.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(
            @RequestBody OrderRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> findByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(orderService.findByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateOrderStatusRequestDto dto) {
        return ResponseEntity.ok(orderService.updateStatus(id, dto.getStatus()));
    }
}
