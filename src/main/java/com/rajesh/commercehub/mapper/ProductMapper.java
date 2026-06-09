package com.rajesh.commercehub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rajesh.commercehub.auth.dto.ProductRequest;
import com.rajesh.commercehub.auth.dto.ProductResponse;
import com.rajesh.commercehub.auth.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	

	//Entity → DTO
	@Mapping(source = "seller.username", target = "sellerName")
	ProductResponse toDto(Product product);
	
	//DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seller", ignore = true)
	Product toEntity(ProductRequest request);

}
