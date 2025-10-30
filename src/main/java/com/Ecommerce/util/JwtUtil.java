package com.Ecommerce.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final String SECRET = "yourSuperSecretKeyForJwtGeneration12345_MakeItLongEnoughForSecurity_2025";
	
	private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000; 
	
	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());


	
	public String generateToken(String userName) {
		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration( new Date(System.currentTimeMillis() + EXPIRE_TIME))
						.signWith(key,SignatureAlgorithm.HS256)
						.compact();
	}
	
	public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
	
	public boolean validateToken(String token) {
		try {
			extractAllClaims(token);
			return true;
		}
		catch (JwtException | IllegalArgumentException e) {
            return false;
        }
	}
      public Claims extractAllClaims(String token) {
    	 return Jwts.parserBuilder()
    			 .setSigningKey(key)
    			 .build()
                 .parseClaimsJws(token)
                 .getBody();
    	 
      }
}
