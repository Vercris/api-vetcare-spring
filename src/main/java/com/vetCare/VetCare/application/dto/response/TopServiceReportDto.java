package com.vetCare.VetCare.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopServiceReportDto {
    private Long serviceId;
    private String serviceName;
    private Long appointmentCount;
}
