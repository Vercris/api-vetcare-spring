package com.vetCare.VetCare.application.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponseDto {
    private Long id;
    private Long userId;
    private List<CartItemResponseDto> items;
    private BigDecimal total;
}
