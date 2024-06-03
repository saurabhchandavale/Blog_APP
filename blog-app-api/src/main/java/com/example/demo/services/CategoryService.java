package com.example.demo.services;

import java.util.List;

import com.example.demo.payload.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	public void deleteCategory(Integer categoryId);

	public List<CategoryDto> getAllCategories();

	public CategoryDto getCategoryById(Integer categoryId);

}
