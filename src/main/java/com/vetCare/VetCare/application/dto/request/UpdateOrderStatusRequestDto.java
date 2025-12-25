package com.vetCare.VetCare.application.dto.request;

import com.vetCare.VetCare.domain.model.enums.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusRequestDto {
    private OrderStatus status;
}
