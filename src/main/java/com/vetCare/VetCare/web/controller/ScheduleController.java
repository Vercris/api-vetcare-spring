package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.ScheduleRequestDto;
import com.vetCare.VetCare.application.dto.response.ScheduleResponseDto;
import com.vetCare.VetCare.application.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> create(@RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findScheduleById(id));
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ScheduleResponseDto>> findByService(@PathVariable Long serviceId) {
        return ResponseEntity.ok(scheduleService.findSchedulesByService(serviceId));
    }

    @GetMapping("/available")
    public ResponseEntity<List<ScheduleResponseDto>> findAvailable() {
        return ResponseEntity.ok(scheduleService.findAvailableSchedules());
    }
}
