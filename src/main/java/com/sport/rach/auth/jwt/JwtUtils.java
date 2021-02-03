package com.sport.rach.auth.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.sport.rach.auth.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${sport.rach.jwtSecret}")
	private String jwtSecret;
	
	@Value("${sport.rach.jwtExpirationMs}")
	private int expirationMs;

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + expirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}" + e.getMessage());			
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is expired: {}" + e.getMessage());	
		} catch (MalformedJwtException e) {
			logger.error("JWT token is invalid: {}" + e.getMessage());	
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}" + e.getMessage());	
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}" + e.getMessage());	
		}
		return false;
	}
}
