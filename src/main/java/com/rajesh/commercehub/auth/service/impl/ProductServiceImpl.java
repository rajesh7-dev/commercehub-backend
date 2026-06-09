package com.rajesh.commercehub.auth.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rajesh.commercehub.auth.dto.ProductRequest;
import com.rajesh.commercehub.auth.dto.ProductResponse;
import com.rajesh.commercehub.auth.entity.Product;
import com.rajesh.commercehub.auth.entity.User;
import com.rajesh.commercehub.auth.repository.ProductRepository;
import com.rajesh.commercehub.auth.repository.UserRepository;
import com.rajesh.commercehub.auth.service.ProductService;
import com.rajesh.commercehub.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	
	private final ProductMapper productMapper;

	
    private final ProductRepository productRepository;
    
    private final UserRepository userRepository;

	
	@Transactional
	@Override
	public ProductResponse addProduct(ProductRequest request, String username) {
		
		
		log.info("User {} is adding product {}", username, request.getName());
		
        User seller = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


         //DTO → Entity
         Product product = productMapper.toEntity(request);
         
         product.setSeller(seller);

      
        Product saved = productRepository.save(product);

        //Entity → DTO
        return productMapper.toDto(saved);

		
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		

		 return productRepository.findAll()
		                .stream()
		                .map(productMapper::toDto)
		                .toList();
		
	}
	
	
	
     @Transactional
	 @Override
	 public ProductResponse updateProduct(Long id, ProductRequest request, String username) {

		    //fetching product
		    Product product = productRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Product not found"));
		    

		    //getting current user
		    User user = userRepository.findByUsername(username)
		            .orElseThrow(() -> new RuntimeException("User not found"));
		    
		    

		    //Ownership checking 
		    boolean isOwner = product.getSeller().getId().equals(user.getId());

		    boolean isAdmin = user.getRole().getName().name().equals("ADMIN");
		    
		    log.info("User {} is updating product {}", username, id);

		    if (!isOwner && !isAdmin) {
		    	 log.warn("Unauthorized update by user {}", username);
		        throw new RuntimeException("You are not allowed to update this product");
	        
		    }

		    log.info("Product {} updated successfully", id);
		    
		    //Updating product
		    product.setName(request.getName());
		    product.setDescription(request.getDescription());
		    product.setPrice(request.getPrice());
		    product.setQuantity(request.getQuantity());

		    Product updated = productRepository.save(product);

		    return productMapper.toDto(updated);
		}

	 @Override
	 public void deleteProduct(Long id, String username) {
		

     Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    boolean isOwner = product.getSeller().getId().equals(user.getId());
    boolean isAdmin = user.getRole().getName().name().equals("ADMIN");

    if (!isOwner && !isAdmin) {
        throw new RuntimeException("You are not allowed to delete this product");
    }

    productRepository.delete(product);

		
	 }

	
	
	
	

}
