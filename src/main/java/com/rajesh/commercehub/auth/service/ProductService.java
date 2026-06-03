package com.rajesh.commercehub.auth.service;

import java.util.List;

import com.rajesh.commercehub.auth.dto.ProductRequest;
import com.rajesh.commercehub.auth.dto.ProductResponse;

public interface ProductService {
	
	

    ProductResponse addProduct(ProductRequest request, String username);

    List<ProductResponse> getAllProducts();

	

}
