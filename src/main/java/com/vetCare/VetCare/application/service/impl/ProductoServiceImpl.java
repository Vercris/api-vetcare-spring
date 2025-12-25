package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.dto.request.ProductRequestDto;
import com.vetCare.VetCare.application.dto.response.ProductResponseDto;
import com.vetCare.VetCare.application.mapper.ProductMapper;
import com.vetCare.VetCare.application.service.ProductService;
import com.vetCare.VetCare.domain.model.Category;
import com.vetCare.VetCare.domain.model.Product;
import com.vetCare.VetCare.domain.model.enums.CategoryType;
import com.vetCare.VetCare.domain.repository.CategoryRepository;
import com.vetCare.VetCare.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto create(ProductRequestDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (category.getType() != CategoryType.PRODUCT) {
            throw new RuntimeException("La categoría seleccionada no es válida para productos");
        }

        Product product = productMapper.toEntity(dto, category);
        // Asegurar que isActive se establezca si no viene en el DTO o por defecto
        if (product.getIsActive() == null) {
            product.setIsActive(true);
        }

        Product saved = productRepository.save(product);

        return productMapper.toDto(saved);
    }

    @Override
    public ProductResponseDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return productMapper.toDto(product);
    }

    @Override
    public List<ProductResponseDto> listActive() {
        return productRepository.findByIsActiveTrue()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductResponseDto> search(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (category.getType() != CategoryType.PRODUCT) {
            throw new RuntimeException("La categoría seleccionada no es válida para productos");
        }

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(category);
        


        Product updated = productRepository.save(product);
        return productMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productRepository.deleteById(id);
    }
}
