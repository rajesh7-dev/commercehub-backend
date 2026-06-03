package com.rajesh.commercehub.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	

private final String SECRET = "mysecretkeymysecretkeymysecretkey123"; // keep long // This used to generate SIGNATURE

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username, String role) {
    	
    	Map<String, Object> claims = new HashMap<>();
    	claims.put("role", role);    // added role inside token
    	
    
        return Jwts.builder()
        		.setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    
    //Extracting role from token
    public String extractRole(String token) {
    	
    	return Jwts.parserBuilder()
    			.setSigningKey(getSignKey())
    			.build()
    			.parseClaimsJws(token)
    			.getBody()
    			.get("role", String.class);
    }
    
    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username);
    }


}
