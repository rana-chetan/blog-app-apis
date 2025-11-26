package com.example.blog.service;

import com.example.blog.DTOs.CategoryDto;
import java.util.List;

public interface CategoryService {

    // Create
    public CategoryDto createCategory(CategoryDto categoryDto);

    // Update
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // Get by Id
    public CategoryDto getCategoryById(Integer categoryId);

    // Get all
    public List<CategoryDto> getAllCategories();

    // Delete
    public void deleteCategory(Integer categoryId);

}
