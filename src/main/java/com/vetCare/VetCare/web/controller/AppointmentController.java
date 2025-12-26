package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.AppointmentRequestDto;
import com.vetCare.VetCare.application.dto.request.UpdateAppointmentStatusDto;
import com.vetCare.VetCare.application.dto.response.AppointmentResponseDto;
import com.vetCare.VetCare.application.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> create(@RequestBody AppointmentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentResponseDto>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateAppointmentStatusDto dto) {
        return ResponseEntity.ok(appointmentService.updateAppointmentStatus(id, dto.getStatus()));
    }
}
