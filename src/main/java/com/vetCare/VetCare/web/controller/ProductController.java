package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.dto.request.ProductRequestDto;
import com.vetCare.VetCare.application.dto.response.ProductResponseDto;
import com.vetCare.VetCare.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(
            @RequestBody ProductRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ProductResponseDto>> listActive() {
        return ResponseEntity.ok(productService.listActive());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> search(
            @RequestParam String name) {

        return ResponseEntity.ok(productService.search(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable Long id,
            @RequestBody ProductRequestDto dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
