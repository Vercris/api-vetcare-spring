package com.vetCare.VetCare.application.dto.request;

import com.vetCare.VetCare.domain.model.enums.AppointmentStatus;
import lombok.Data;

@Data
public class UpdateAppointmentStatusDto {
    private AppointmentStatus status;
}
