package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.ServiceRequestDto;
import com.vetCare.VetCare.application.dto.response.ServiceResponseDto;
import com.vetCare.VetCare.application.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<ServiceResponseDto> create(@RequestBody ServiceRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponseDto>> findAll() {
        return ResponseEntity.ok(serviceService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDto> update(@PathVariable Long id, @RequestBody ServiceRequestDto dto) {
        return ResponseEntity.ok(serviceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
