package com.rajesh.commercehub;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/welcome")
	public String greet() {
		return "Welcome to Commercehub";
	}
	
	
@GetMapping("/debug")
public String debug(Authentication auth) {
    return auth.getAuthorities().toString();
}


}
