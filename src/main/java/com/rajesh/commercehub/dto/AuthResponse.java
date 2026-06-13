package com.rajesh.commercehub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
	
	private String token; 

}
