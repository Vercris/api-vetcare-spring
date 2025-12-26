package com.vetCare.VetCare.application.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Long categoryId;
    private String imageUrl;
    private String sku;
}
