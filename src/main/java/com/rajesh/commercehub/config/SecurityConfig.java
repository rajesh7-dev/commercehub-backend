package com.rajesh.commercehub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rajesh.commercehub.security.JwtAccessDeniedHandler;
import com.rajesh.commercehub.security.JwtAuthEntryPoint;
import com.rajesh.commercehub.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	    @Autowired
	    private JwtAuthFilter jwtAuthFilter; 
	    
	    
	    @Autowired
	    private JwtAuthEntryPoint authEntryPoint;
	    
	    @Autowired
	    private JwtAccessDeniedHandler accessDeniedHandler;


	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http
	            .csrf(csrf -> csrf.disable())   // disabling csrf for Postman
	            .exceptionHandling(ex -> ex
	            		.authenticationEntryPoint(authEntryPoint) //It is triggered when authentication fails
	            		.accessDeniedHandler(accessDeniedHandler) //It is triggered when Authorization fails
	            )
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/auth/register" , "/api/auth/login")
	                .permitAll()  //allowing register/login
	                
                    .requestMatchers("/user/**").hasRole("USER")
                    .requestMatchers("/seller/**").hasRole("SELLER")
                    .requestMatchers("/admin/**").hasRole("ADMIN")

	                
	                .anyRequest().authenticated()
	            )
	            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class );

	        return http.build();
	    }

	
@Bean
public PasswordEncoder passwordEncoder() {
	
    return new BCryptPasswordEncoder();
}

	
	
}
