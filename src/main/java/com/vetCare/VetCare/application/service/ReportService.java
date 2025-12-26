package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.application.dto.response.TopServiceReportDto;

import java.util.List;

public interface ReportService {
    List<TopServiceReportDto> getTopServices();
}
