package com.example.springsecurityexercise.application;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.springsecurityexercise.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
	private final JwtUtil jwtUtil;

	public String parseToken(String token) {
		Claims claims = jwtUtil.decode(token);
		return claims.get("email", String.class);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		Claims claims = jwtUtil.decode(token);
		return jwtUtil.validateToken(claims, userDetails);
	}

}
