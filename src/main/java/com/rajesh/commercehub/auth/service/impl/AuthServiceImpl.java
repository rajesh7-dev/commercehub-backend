package com.rajesh.commercehub.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rajesh.commercehub.auth.dto.AuthResponse;
import com.rajesh.commercehub.auth.dto.LoginRequest;
import com.rajesh.commercehub.auth.dto.RegisterRequest;
import com.rajesh.commercehub.auth.dto.UserResponse;
import com.rajesh.commercehub.auth.entity.Role;
import com.rajesh.commercehub.auth.entity.User;
import com.rajesh.commercehub.auth.repository.RoleRepository;
import com.rajesh.commercehub.auth.repository.UserRepository;
import com.rajesh.commercehub.auth.service.AuthService;
import com.rajesh.commercehub.enums.RoleType;
import com.rajesh.commercehub.security.JwtUtil;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	private final JwtUtil jwtUtil; 
	
	

	@Override
	public UserResponse register(RegisterRequest request) {


     // Check if email exists
    if(userRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email already exists");
    }
 

      //Get role
    Role role = roleRepository.findByName(RoleType.USER)
            .orElseThrow(() -> new RuntimeException("Role not found"));

		

     //Map DTO → Entity
    User user = new User();
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setEmail(request.getEmail());
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(role);

    

     //Save
     User savedUser = userRepository.save(user);

     //Return response
     return UserResponse.builder()
               .id(savedUser.getId())
               .firstName(savedUser.getFirstName())
               .lastName(savedUser.getLastName())
               .email(savedUser.getEmail())
               .username(savedUser.getUsername())
               .role(savedUser.getRole().getName().name())
               .build();

		
	}



	@Override
	public AuthResponse login(LoginRequest request) {


		    //Find user by email or user name
		    User user = userRepository.findByEmail(request.getIdentifier())
		    		.orElseGet(() -> userRepository.findByUsername(request.getIdentifier())
		            .orElseThrow(() -> new RuntimeException("User not found")));

		    //Check password
		    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
		        throw new RuntimeException("Invalid password");
		    }

		    String token = jwtUtil.generateToken(
		    		user.getUsername(),
		    		user.getRole().getName().name()); //Role added
		    
		    
		    // Return response
		    return AuthResponse.builder()
		    		.token(token)
		    		.build();

		
	}

}
