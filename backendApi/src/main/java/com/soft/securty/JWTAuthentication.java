package com.soft.securty;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.soft.serviceimpl.CustomUserDetailService;
import com.soft.serviceimpl.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTAuthentication extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtservice;
	
	@Autowired
	private CustomUserDetailService userdetailservice;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String authHeader = request.getHeader("Authorization");
		
//		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
//			filterChain.doFilter(request, response);
//			return ;
//		}
		
		if(authHeader == null || 
				   !(authHeader.startsWith("Bearer ") || authHeader.startsWith("Token "))) { 
			filterChain.doFilter(request, response);
			return ;
		}
		
//		String jwt = authHeader.substring(7);
		
		String jwt = authHeader.startsWith("Bearer ")
		        ? authHeader.substring(7)
		        : authHeader.substring(6); // Token <jwt>
		
		String userName = jwtservice.extractUserName(jwt);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(userName!=null && authentication==null) {
//			authentication
			UserDetails userDetails=userdetailservice.loadUserByUsername(userName);
			
			if(jwtservice.tokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken=
						new UsernamePasswordAuthenticationToken(
						 userDetails,null,userDetails.getAuthorities());
				
				authenticationToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			};
			
			
		}
		
		filterChain.doFilter(request, response);
	}


}
