package com.vetCare.VetCare.application.dto.response;

import com.vetCare.VetCare.domain.model.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderResponseDto {
    private Long id;
    private String orderNumber;
    private OrderStatus status;
    private BigDecimal total;
    private List<OrderItemResponseDto> items;


}
