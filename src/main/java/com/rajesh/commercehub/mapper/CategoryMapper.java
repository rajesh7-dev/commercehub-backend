package com.rajesh.commercehub.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rajesh.commercehub.dto.CategoryRequest;
import com.rajesh.commercehub.dto.CategoryResponse;
import com.rajesh.commercehub.entity.Category;

@Mapper(componentModel = "Spring")
public interface CategoryMapper {
	
	//Entity to DTO
	CategoryResponse toDto(Category category);
	
	
	//DTO to Entity
	@Mapping(target = "id", ignore = true)
	Category toEntity(CategoryRequest request);

}
