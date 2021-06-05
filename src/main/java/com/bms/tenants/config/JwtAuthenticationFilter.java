package com.bms.tenants.config;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

//		setFilterProcessesUrl("/api/services/controller/user/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		
//		try {
////			String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
////			String test2 = IOUtils.toString(request.getReader());;
////			System.out.println(test2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String username = obtainUsername(request);
		username = (username != null) ? username : "";
		username = username.trim();
		String password = obtainPassword(request);
		password = (password != null) ? password : "";
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("shubhamgoel@yahoo.com", "44447777");
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		Authentication authenticte = authenticationManager.authenticate(authRequest);
		return authenticte;

	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = Jwts.builder()
			.setSubject(authResult.getName())
			.claim("authorities", authResult.getAuthorities())
			.setIssuedAt(new Date())
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
			.signWith(Keys.hmacShaKeyFor("secursecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuree".getBytes()))
			.compact();
		Cookie cookie = new Cookie("Authorization", "Bearer "+token);
//		cookie.setHttpOnly(true);
//		cookie.setSecure(true);
		response.addCookie(cookie);
		response.addHeader("Authorization", "Bearer "+token);
	}
}
