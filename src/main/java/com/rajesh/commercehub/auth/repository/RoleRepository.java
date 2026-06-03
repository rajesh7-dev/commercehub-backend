package com.rajesh.commercehub.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajesh.commercehub.auth.entity.Role;
import com.rajesh.commercehub.enums.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	
	 Optional<Role> findByName(RoleType name);

}
