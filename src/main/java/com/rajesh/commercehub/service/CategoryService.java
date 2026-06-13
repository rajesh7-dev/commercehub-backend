package com.rajesh.commercehub.service;

import java.util.List;

import com.rajesh.commercehub.dto.CategoryRequest;
import com.rajesh.commercehub.dto.CategoryResponse;

public interface CategoryService {
	
	CategoryResponse createCategory(CategoryRequest request);
	
	
    List<CategoryResponse> getAllCategories();

}
