package com.rajesh.commercehub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rajesh.commercehub.auth.dto.UserResponse;
import com.rajesh.commercehub.auth.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	
	@Mapping(source = "role.name", target = "role")
	UserResponse toDto(User user);

}
