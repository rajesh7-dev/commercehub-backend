package com.rajesh.commercehub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rajesh.commercehub.dto.CategoryRequest;
import com.rajesh.commercehub.dto.CategoryResponse;
import com.rajesh.commercehub.entity.Category;
import com.rajesh.commercehub.mapper.CategoryMapper;
import com.rajesh.commercehub.repository.CategoryRepository;
import com.rajesh.commercehub.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	
	private final CategoryRepository categoryRespository;
	
	
	private final CategoryMapper categorymapper;
	
	

	@Override
	public CategoryResponse createCategory(CategoryRequest request) {

		//duplicate checking
		categoryRespository.findByName(request.getName())
		            .ifPresent(c -> {
		            	throw new RuntimeException("Category not found");
		            });
		
		 Category category = categorymapper.toEntity(request);
		 
		 Category saved = categoryRespository.save(category);
		
		return categorymapper.toDto(saved);
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
        
		return categoryRespository.findAll()
				.stream()
				.map(categorymapper::toDto)
				.toList();
	}
	
	

	
}
