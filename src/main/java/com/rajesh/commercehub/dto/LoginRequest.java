package com.rajesh.commercehub.dto;

import lombok.Data;

@Data
public class LoginRequest {
	

    private String identifier;      // email OR username
    private String password;


}
