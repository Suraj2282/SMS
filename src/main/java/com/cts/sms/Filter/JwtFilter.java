package com.cts.sms.Filter;

import java.io.IOException;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.sms.service.CustomUserDetailsService;
import com.cts.sms.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//every request the filter gets activated and on chain of filter it must be activated once

public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService service;
	
	@Autowired
	org.springframework.context.ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTdXJhajIyODIyMzI1IiwiaWF0IjoxNzM0ODY0OTUwLCJleHAiOjE3MzQ4NjUwNTh9.Qm16hlrrff4x5tjHOQ2uanGMdSm4-EO-O01xtAZIDGo
//		received from client on every request
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer "))
		{
			token = authHeader.substring(7);
			username= service.extractUsername(token);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = context.getBean(CustomUserDetailsService.class).loadUserByUsername(username);
			if(service.validateToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
