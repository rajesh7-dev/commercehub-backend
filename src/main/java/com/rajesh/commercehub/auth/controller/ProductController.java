package com.rajesh.commercehub.auth.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.commercehub.auth.dto.ProductRequest;
import com.rajesh.commercehub.auth.dto.ProductResponse;
import com.rajesh.commercehub.auth.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	
	

     private final ProductService productService;

    // public (USER access)
    @PreAuthorize("hasAnyRole('USER','SELLER','ADMIN')") 
    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.getAllProducts();
    }

    // SELLER adds product
    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping
    public ProductResponse addProduct(
            @RequestBody ProductRequest request,
            Authentication auth
    ) {
        String username = auth.getName();

        return productService.addProduct(request, username);
    }


}
