package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.CategoryDto;
import com.example.demo.reporitries.CategoryRepo;
import com.example.demo.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category save = this.categoryRepo.save(category);
		return this.modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category byId = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id", categoryId));
		byId.setCategoryTitle(categoryDto.getCategoryTitle());
		byId.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.categoryRepo.save(byId);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}	

	@Override
	public void deleteCategory(Integer categoryId) {
		Category byId = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id", categoryId));
		this.categoryRepo.delete(byId);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> all = this.categoryRepo.findAll();
		List<CategoryDto> categoryDto = all.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDto;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category byId = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id", categoryId));
		return this.modelMapper.map(byId, CategoryDto.class);
	}

}
