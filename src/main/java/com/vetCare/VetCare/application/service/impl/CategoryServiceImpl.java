package com.vetCare.VetCare.application.service.impl;

import com.vetCare.VetCare.application.service.CategoryService;
import com.vetCare.VetCare.domain.model.Category;
import com.vetCare.VetCare.domain.model.enums.CategoryType;
import com.vetCare.VetCare.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private  final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByType(CategoryType type) {
        return categoryRepository.findByType(type);
    }
}
