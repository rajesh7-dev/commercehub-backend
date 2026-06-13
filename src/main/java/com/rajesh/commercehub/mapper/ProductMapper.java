package com.rajesh.commercehub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rajesh.commercehub.dto.ProductRequest;
import com.rajesh.commercehub.dto.ProductResponse;
import com.rajesh.commercehub.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	

	//Entity → DTO
	@Mapping(source = "seller.username", target = "sellerName")
	@Mapping(source = "category.name", target = "categoryName")
	ProductResponse toDto(Product product);
	
	//DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "category", ignore = true)
	Product toEntity(ProductRequest request);

}
