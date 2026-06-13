package com.rajesh.commercehub.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.commercehub.dto.PaginationResponse;
import com.rajesh.commercehub.dto.ProductRequest;
import com.rajesh.commercehub.dto.ProductResponse;
import com.rajesh.commercehub.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")  //Controller binding
public class ProductController {
	
	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);


     private final ProductService productService;
     
     

    // public (USER access)
    @PreAuthorize("hasAnyRole('USER','SELLER','ADMIN')") 
    @GetMapping("/all")
    public List<ProductResponse> getProducts() {
        return productService.getAllProducts(); 
        
    }

    
    
    
    // SELLER adds product
    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping
    public ProductResponse addProduct(
            @Valid @RequestBody ProductRequest request,
            Authentication auth
    ) {
        String username = auth.getName();

        return productService.addProduct(request, username);
    }
    
    
    
    
//UPDATE product
@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
@PutMapping("/{id}")
public ProductResponse updateProduct(
        @PathVariable Long id,
        @Valid @RequestBody ProductRequest request,
        Authentication auth
) {
	
    String username = auth.getName();
    log.info("Update API called for product {}", id);
    return productService.updateProduct(id, request, username);
}





//deleting product
@PreAuthorize("hasAnyRole('SELLER','ADMIN')")
@DeleteMapping("/{id}")
public String deleteProduct(@PathVariable Long id, Authentication auth) {
    productService.deleteProduct(id, auth.getName());
    return "Product deleted successfully";
}



@GetMapping
   public PaginationResponse<ProductResponse> getAllProducts(Pageable pageable) {
	
       Page<ProductResponse> page = productService.getAllProducts(pageable);
       
       

       return new PaginationResponse<>(
                  page.getContent(),
                  page.getNumber(),
                  page.getSize(),
                  page.getTotalElements(),
                  page.getTotalPages(),
                  page.isLast()
          );

       
   }



@GetMapping("/search")
public PaginationResponse<ProductResponse> searchProducts(
        @RequestParam String keyword,
        Pageable pageable) {

    Page<ProductResponse> page =
            productService.searchProducts(keyword, pageable);

    return new PaginationResponse<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.isLast()
    );
}



@GetMapping("/filter")
public PaginationResponse<ProductResponse> filterProducts(
        @RequestParam double price,
        Pageable pageable) {

    Page<ProductResponse> page =
            productService.filterByPrice(price, pageable);

    return new PaginationResponse<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.isLast()
    );
}


    @GetMapping("/by-category")
    public PaginationResponse<ProductResponse> getProductsByCategory(
    		@RequestParam Long categoryId,
    		Pageable pageable){
    	
    	Page<ProductResponse> page = 
    			productService.getProductsByCategory(categoryId, pageable);
    	
    	return new PaginationResponse<>(
    			page.getContent(),
    			page.getNumber(),
    			page.getSize(),
    			page.getTotalElements(),
    			page.getTotalPages(),
    			page.isLast()
    			
    		);	
    			
    }



}
