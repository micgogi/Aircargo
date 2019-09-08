package com.micgogi.aircargo.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
    
    
    private String jwtSecret="rahul_gogyani";
    private int jwtExpiration = 86400;
    
    public String generateJwtToken(Authentication authentication){
    User users = (User) authentication.getPrincipal();
    return Jwts.builder()
            .setSubject((users.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    		
    }
    
    public boolean validateToken(String authToken){
    	try{
    		Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
    		return true;
    	}catch(SignatureException e){
    		  LOGGER.error("Invalid JWT signature -> Message: {} ", e);
    	}catch(MalformedJwtException e){
    		  LOGGER.error("Invalid JWT token -> Message: {} ", e);
    	}catch(ExpiredJwtException e){
    		  LOGGER.error("Expired JWT token -> Message: {} ", e);
    	}catch(UnsupportedJwtException e){
    		LOGGER.error("Expired JWT token -> Message: {} ", e);
    	}catch(IllegalArgumentException e){
    		LOGGER.error("JWT claims string is empty -> Message: {}", e);
    	}
    	return false;
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}
