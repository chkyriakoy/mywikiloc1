package com.example.mywikiloc.jwt;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.mywikiloc.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
	
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24H
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	// secret key from applications file to set the size of the token
	@Value("{app.jwt.secret}")
	private String secretKey;
	
	public String generateAccessToken(User user) {
		String[] roles = {"false","false","false"};
		
		if(user.isAdmin())
			roles[0] = "true";
		if(user.isEditor())
			roles[1] = "true";
		if(user.isViewer())
			roles[2] = "true";
		
				
		return Jwts.builder()
			.setSubject(user.getId() + "," + user.getEmail())
			.claim("roles", Arrays.toString(roles))
			.setIssuer("MyWikiLoc")
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
			.signWith(SignatureAlgorithm.HS512, secretKey)
			.compact();
	}
	
	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			LOGGER.error("JWT expired", ex);
		} catch (IllegalArgumentException ex) {
			LOGGER.error("Token is null", ex);
		} catch (MalformedJwtException ex) {
			LOGGER.error("Jwt is invalid", ex);
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("Jwt is not supported", ex);
		} catch (SignatureException ex) {
			LOGGER.error("Signature validation failed", ex);
		}
		return false;
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	public Claims parseClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
	}
}
