package com.vetCare.VetCare.application.mapper;

import com.vetCare.VetCare.application.dto.request.ProductRequestDto;
import com.vetCare.VetCare.application.dto.response.ProductResponseDto;
import com.vetCare.VetCare.domain.model.Category;
import com.vetCare.VetCare.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductRequestDto dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(category);
        return product;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategoryName(product.getCategory().getName());
        return dto;
    }
}
