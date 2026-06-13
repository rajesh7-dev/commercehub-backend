package com.rajesh.commercehub.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rajesh.commercehub.dto.ProductRequest;
import com.rajesh.commercehub.dto.ProductResponse;
import com.rajesh.commercehub.entity.Category;
import com.rajesh.commercehub.entity.Product;
import com.rajesh.commercehub.entity.User;
import com.rajesh.commercehub.mapper.ProductMapper;
import com.rajesh.commercehub.repository.CategoryRepository;
import com.rajesh.commercehub.repository.ProductRepository;
import com.rajesh.commercehub.repository.UserRepository;
import com.rajesh.commercehub.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	
	private final ProductMapper productMapper;

	
    private final ProductRepository productRepository;
    
    private final UserRepository userRepository;
    
    private final CategoryRepository categoryRepostiory;


    //Adding new product(SELLER, ADMIN )
	@Transactional
	@Override
	public ProductResponse addProduct(ProductRequest request, String username) {
		
		
		log.info("User {} is adding product {}", username, request.getName());
		
        User seller = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepostiory
        		           .findById(request.getCategoryId())
        		           .orElseThrow(() -> new RuntimeException("Category not found"));

         //DTO → Entity
         Product product = productMapper.toEntity(request);
         
         product.setSeller(seller);
         
         product.setCategory(category);

      
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
	
	
	 // Updating the product 
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

     //Delete product by Id
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

	 //Fetching all products with Pagination
	 @Override
	 public Page<ProductResponse> getAllProducts(Pageable pageable) {
		
		return productRepository.findAll(pageable)
				.map(productMapper::toDto);
	 }

	 
	 //Search products by name
	 @Override
	 public Page<ProductResponse> searchProducts(String keyword, Pageable pageable) {

		 return productRepository
		            .findByNameContainingIgnoreCase(keyword, pageable)
		            .map(productMapper::toDto);
	 }

	 
	//Filtering products less than price
	 @Override
	 public Page<ProductResponse> filterByPrice(double price, Pageable pageable) {
		
		    return productRepository
                         .findByPriceLessThan(price, pageable)
                         .map(productMapper::toDto);
	 }

	 
	 //Filtering products by category
	 @Override
	 public Page<ProductResponse> getProductsByCategory(Long categoryId, Pageable pageable) {

		 return productRepository.findByCategory_Id(categoryId, pageable)
				 .map(productMapper::toDto);
	 }

	 
	 
	
	
	
	
	

}
