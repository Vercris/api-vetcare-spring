package com.vetCare.VetCare.application.dto.request;

import lombok.Data;

@Data
public class AddToCartRequestDto {
    private Long productId;
    private Integer quantity;
}
