package com.rajesh.commercehub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rajesh.commercehub.dto.AuthResponse;
import com.rajesh.commercehub.dto.LoginRequest;
import com.rajesh.commercehub.dto.RegisterRequest;
import com.rajesh.commercehub.dto.UserResponse;
import com.rajesh.commercehub.entity.Role;
import com.rajesh.commercehub.entity.User;
import com.rajesh.commercehub.enums.RoleType;
import com.rajesh.commercehub.mapper.UserMapper;
import com.rajesh.commercehub.repository.RoleRepository;
import com.rajesh.commercehub.repository.UserRepository;
import com.rajesh.commercehub.security.JwtUtil;
import com.rajesh.commercehub.service.AuthService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	private final JwtUtil jwtUtil; 
	
	
	private final UserMapper userMapper;
	
	
	@Transactional
	@Override
	public UserResponse register(RegisterRequest request) {


     // Check if email exists
    if(userRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email already exists");
    }
  
    log.info("Registering user with email {}", request.getEmail());


      //Get role
    Role role = roleRepository.findByName(RoleType.SELLER)
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

     //Entity → DTO
     return userMapper.toDto(savedUser);

		
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
