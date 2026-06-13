package com.rajesh.commercehub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.commercehub.dto.AuthResponse;
import com.rajesh.commercehub.dto.LoginRequest;
import com.rajesh.commercehub.dto.RegisterRequest;
import com.rajesh.commercehub.dto.UserResponse;
import com.rajesh.commercehub.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor 
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
	    return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/login") 
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
	    return ResponseEntity.ok(authService.login(request));
	}


}
