package com.rajesh.commercehub.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajesh.commercehub.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	

	    boolean existsByEmail(String email);

	    boolean existsByUsername(String username);

	    Optional<User> findByEmail(String identifier);
	    
	    Optional<User> findByUsername(String identifier);


}
