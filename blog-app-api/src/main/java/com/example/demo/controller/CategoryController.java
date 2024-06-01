package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CategoryDto;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCatepory(@RequestBody CategoryDto categoryDto){
		CategoryDto category = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.CREATED);
	}
	@PutMapping("{catId}")
	public ResponseEntity<CategoryDto> updateCatepory(@RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		CategoryDto category = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	@DeleteMapping("{catId}")
	public ResponseEntity<ApiResponse> deleteCatepory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is Deleted",true),HttpStatus.OK);
	}
	
	@GetMapping("{catId}")
	public ResponseEntity<CategoryDto> getCateporyById(@PathVariable Integer catId){
		CategoryDto category = this.categoryService.getCategoryById(catId);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCateporyById(){
		 List<CategoryDto> allCategories = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(allCategories,HttpStatus.OK);
	}


}
 