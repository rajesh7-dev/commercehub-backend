package com.rajesh.commercehub.service;

import com.rajesh.commercehub.dto.AuthResponse;
import com.rajesh.commercehub.dto.LoginRequest;
import com.rajesh.commercehub.dto.RegisterRequest;
import com.rajesh.commercehub.dto.UserResponse;

public interface AuthService {
	
     UserResponse register(RegisterRequest request); 
     
     AuthResponse login(LoginRequest request);

}
