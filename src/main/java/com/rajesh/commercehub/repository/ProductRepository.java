package com.rajesh.commercehub.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajesh.commercehub.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	
	//Seller products
    List<Product> findBySellerId(Long sellerId);

    //Search (pagination support)
	Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

	//Filter by price
	Page<Product> findByPriceLessThan(double price, Pageable pageable);

	//Filter by category
	Page<Product> findByCategory_Id(Long categoryId, Pageable pageable);


}
