package com.rajesh.commercehub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rajesh.commercehub.dto.ProductRequest;
import com.rajesh.commercehub.dto.ProductResponse;


public interface ProductService {
	
	

    ProductResponse addProduct(ProductRequest request, String username);

    List<ProductResponse> getAllProducts();

	ProductResponse updateProduct(Long id, ProductRequest request, String username);

	void deleteProduct(Long id, String username);
	

	Page<ProductResponse> getAllProducts(Pageable pageable);

	Page<ProductResponse> searchProducts(String keyword, Pageable pageable);

	Page<ProductResponse> filterByPrice(double price, Pageable pageable);
	
    Page<ProductResponse> getProductsByCategory(Long categoryId, Pageable pageable);

	

}
