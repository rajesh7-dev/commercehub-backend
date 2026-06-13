package com.rajesh.commercehub.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.commercehub.dto.CategoryRequest;
import com.rajesh.commercehub.dto.CategoryResponse;
import com.rajesh.commercehub.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	
	@PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
	@PostMapping
	public CategoryResponse createCategory(@RequestBody CategoryRequest request) {
		
		return categoryService.createCategory(request);
	}
	
	
	
	@GetMapping
	public List<CategoryResponse> getAllCategories(){
		
		return categoryService.getAllCategories();
		
	}
	
	

}
