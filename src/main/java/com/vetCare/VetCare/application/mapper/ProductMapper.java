package com.vetCare.VetCare.application.mapper;

import com.vetCare.VetCare.application.dto.request.ProductRequestDto;
import com.vetCare.VetCare.application.dto.response.ProductResponseDto;
import com.vetCare.VetCare.domain.model.Category;
import com.vetCare.VetCare.domain.model.Product;

public interface ProductMapper {
    Product toEntity(ProductRequestDto dto, Category category);
    ProductResponseDto toDto(Product product);
}
