package com.rajesh.commercehub.auth.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rajesh.commercehub.auth.dto.ProductRequest;
import com.rajesh.commercehub.auth.dto.ProductResponse;
import com.rajesh.commercehub.auth.entity.Product;
import com.rajesh.commercehub.auth.entity.User;
import com.rajesh.commercehub.auth.repository.ProductRepository;
import com.rajesh.commercehub.auth.repository.UserRepository;
import com.rajesh.commercehub.auth.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	

    private final ProductRepository productRepository;
    
    private final UserRepository userRepository;

	
	
	@Override
	public ProductResponse addProduct(ProductRequest request, String username) {
		

        User seller = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .seller(seller)
                .build();

        Product saved = productRepository.save(product);

        return mapToResponse(saved);

		
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		

		 return productRepository.findAll()
		                .stream()
		                .map(this::mapToResponse)
		                .toList();
		
	}
	
	

	 private ProductResponse mapToResponse(Product p) {
	        return ProductResponse.builder()
	                .id(p.getId())
	                .name(p.getName())
	                .description(p.getDescription())
	                .price(p.getPrice())
	                .build();
	    }

	
	
	
	

}
