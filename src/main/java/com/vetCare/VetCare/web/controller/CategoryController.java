package com.vetCare.VetCare.web.controller;

import com.vetCare.VetCare.application.service.CategoryService;
import com.vetCare.VetCare.domain.model.Category;
import com.vetCare.VetCare.domain.model.enums.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Category>> findByType(@PathVariable CategoryType type) {
        return ResponseEntity.ok(categoryService.findByType(type));
    }
}
