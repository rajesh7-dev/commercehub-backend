package com.rajesh.commercehub.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {// For every request we want to request for this filter only once
	

    @Autowired
    private JwtUtil jwtUtil;
    
    
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
	    String path = request.getServletPath();

	    return path.startsWith("/v3/api-docs") ||
	           path.startsWith("/swagger-ui") ||
	           path.equals("/swagger-ui.html");
	}
	
	

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

    
    	
        // Getting Authorization header
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;
        
        String role = null;

        System.out.println("authHeader :" + authHeader);
        
        
        
        // Checking if header contains Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
            role = jwtUtil.extractRole(token);
            
            System.out.println("Token: " + token);
            
            System.out.println("Username: " + username);
        }

        //Validating the token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            if (jwtUtil.validateToken(token, username)) {

            	
            	List<SimpleGrantedAuthority> authorities = 
            			List.of(new SimpleGrantedAuthority("ROLE_" +role));
            	
            	
                //Marking user as authenticated
                var authToken = new UsernamePasswordAuthenticationToken(
                        username, null, authorities
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 4. Continue request
        filterChain.doFilter(request, response);
    }


 

}
