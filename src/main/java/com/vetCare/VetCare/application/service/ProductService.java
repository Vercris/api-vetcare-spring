package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.application.dto.request.ProductRequestDto;
import com.vetCare.VetCare.application.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto create(ProductRequestDto dto);

    ProductResponseDto findById(Long id);

    List<ProductResponseDto> listActive();

    List<ProductResponseDto> search(String name);

    List<ProductResponseDto> findAll();

    ProductResponseDto update(Long id, ProductRequestDto dto);

    void delete(Long id);
}
