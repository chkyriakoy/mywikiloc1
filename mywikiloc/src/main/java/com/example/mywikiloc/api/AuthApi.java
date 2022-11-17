package com.example.mywikiloc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mywikiloc.jwt.JwtTokenUtil;
import com.example.mywikiloc.model.MyUserDetails;
import com.example.mywikiloc.model.User;

@RestController
public class AuthApi {
	
	@Autowired 
	AuthenticationManager authManager;
	@Autowired
	JwtTokenUtil jwtUtil;
	
	// curl -v -H "Content-Type: application/json" -d "{\"email\": \"jDoe@myemail.com\", \"password\":\"password1\"}" localhost:8080/auth/login
	// curl -v -H "Content-Type: application/json" -d "{\"email\": \"JackDoe@myemail.com\", \"password\":\"password3\"}" localhost:8080/auth/login
	 
	@PostMapping("/auth/login")
	    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
	        try {
	            Authentication authentication = authManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            request.getEmail(), request.getPassword())
	            );
	             
	            MyUserDetails myUser = (MyUserDetails) authentication.getPrincipal();
	            User user = myUser.getLogedUser();
	            String accessToken = jwtUtil.generateAccessToken(user);
	            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
	             
	            return ResponseEntity.ok().body(response);
	             
	        } catch (BadCredentialsException ex) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	    }
	
	
	
}
