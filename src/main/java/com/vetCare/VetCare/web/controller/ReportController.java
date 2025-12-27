package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.response.TopServiceReportDto;
import com.vetCare.VetCare.application.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/top-services")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TopServiceReportDto>> getTopServices() {
        return ResponseEntity.ok(reportService.getTopServices());
    }
}
