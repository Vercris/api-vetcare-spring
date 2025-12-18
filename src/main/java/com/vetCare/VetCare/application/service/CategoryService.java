package com.vetCare.VetCare.application.service;

import com.vetCare.VetCare.domain.model.Category;
import com.vetCare.VetCare.domain.model.enums.CategoryType;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    List<Category> findByType(CategoryType type);
}
