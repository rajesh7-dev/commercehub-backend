package com.rajesh.commercehub.auth.service;

import com.rajesh.commercehub.auth.dto.AuthResponse;
import com.rajesh.commercehub.auth.dto.LoginRequest;
import com.rajesh.commercehub.auth.dto.RegisterRequest;
import com.rajesh.commercehub.auth.dto.UserResponse;

public interface AuthService {
	
     UserResponse register(RegisterRequest request); 
     
     AuthResponse login(LoginRequest request);

}
