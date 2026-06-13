package com.rajesh.commercehub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rajesh.commercehub.dto.UserResponse;
import com.rajesh.commercehub.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	
	@Mapping(source = "role.name", target = "role")
	UserResponse toDto(User user);

}
