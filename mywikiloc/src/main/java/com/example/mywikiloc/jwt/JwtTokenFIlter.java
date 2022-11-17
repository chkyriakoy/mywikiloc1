package com.example.mywikiloc.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.mywikiloc.model.MyUserDetails;
import com.example.mywikiloc.model.User;

//curl -H "Authorization: SomeAutho <token>" localhost:8080/routes

@Component
public class JwtTokenFIlter extends OncePerRequestFilter {

	@Autowired 
	private JwtTokenUtil jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String header = request.getHeader("Authorization");
		System.out.println("AUthorization header: "+ header);
		
		if(!hasAuthorizationHeader(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String accessToken = getAccessToken(request);
		
		if(!jwtUtil.validateAccessToken(accessToken)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		setAuthenticationContext(accessToken, request);
		filterChain.doFilter(request, response);
		
		
	}
	
	private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
		MyUserDetails usDetail = getUserDetails(accessToken);
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usDetail, null, null);
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private MyUserDetails getUserDetails(String accessToken) {
		User userDetails = new User();
		
		String[] subjectArray = jwtUtil.getSubject(accessToken).split(",");
		System.out.println("jwtUtil subject: "+subjectArray[0]);
		userDetails.setId(Integer.parseInt(subjectArray[0]));
		userDetails.setEmail(subjectArray[1]);
		MyUserDetails myUserDet = new MyUserDetails(userDetails);
		return myUserDet;
	}
	
	
	private boolean hasAuthorizationHeader(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		System.out.println("AUthorization header: "+ header);
		
		if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
			return false;
		}
		return true;
	}
	
	private String getAccessToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		//curl -H "Authorization: Bearer <token>" localhost:8080/routes
		String token = header.split(" ")[1].trim();
		System.out.println("Access token: "+ token);
		return token;
	}

}
