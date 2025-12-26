package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.dto.response.TopServiceReportDto;
import com.vetCare.VetCare.application.service.ReportService;
import com.vetCare.VetCare.domain.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<TopServiceReportDto> getTopServices() {
        return appointmentRepository.findTopServices();
    }
}
