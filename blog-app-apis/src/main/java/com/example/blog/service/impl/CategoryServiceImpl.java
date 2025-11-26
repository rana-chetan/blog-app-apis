package com.example.blog.service.impl;

import com.example.blog.DTOs.CategoryDto;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.model.Category;
import com.example.blog.repository.categoryRepository;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    categoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.DtoToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return this.CategoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        category1.setCategoryTitle(category1.getCategoryTitle());
        category1.setCategoryDescription(category1.getCategoryDescription());
        Category savedCategory = categoryRepository.save(category1);
        return this.CategoryToDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return this.CategoryToDto(category1);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> all = categoryRepository.findAll();
        List<CategoryDto> list = all.stream().map((category) -> this.CategoryToDto(category)).toList();
        return list;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(category1);
    }

    // Converting DTO to Entity
    private Category DtoToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        return category;
    }

    // Converting Entity to DTO
    private CategoryDto CategoryToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDescription(category.getCategoryDescription());
        return categoryDto;
    }
}
