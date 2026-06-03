package com.rajesh.commercehub.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
	

    private String identifier;      // email OR username
    private String password;


}
