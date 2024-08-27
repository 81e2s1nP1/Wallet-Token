package com.pa.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

//Execute Before Executing Spring Security Filters
//Validate the JWT Token and Provides user details to Spring Security for Authentication

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
			CustomUserDetailsService customUserDetailsService) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getTokenFromRequest(request);
		
		//Validate
		if(StringUtils.hasText(token) && jwtTokenProvider.ValidateToken(token)) {
			String username = jwtTokenProvider.getUsername(token);
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
		String Bearer = request.getHeader("Authorization");
		if(StringUtils.hasText(Bearer) && Bearer.startsWith("Bearer ")) {
			return Bearer.substring(7, Bearer.length());
		}
		return null;  
	}
	
	
}
