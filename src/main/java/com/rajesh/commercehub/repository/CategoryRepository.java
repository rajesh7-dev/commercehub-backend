package com.rajesh.commercehub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajesh.commercehub.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	
	//Finding by name (for duplicate check)
	Optional<Category> findByName(String name);

	

}
