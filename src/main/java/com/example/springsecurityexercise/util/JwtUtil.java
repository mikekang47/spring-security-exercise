package com.example.springsecurityexercise.util;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtil {
	private final Key key;

	public JwtUtil() {
		byte[] arr = new byte[32];
		new Random().nextBytes(arr);
		Encoders.BASE64.encode(arr);
		this.key = Keys.hmacShaKeyFor(arr);
	}

	public String encode(Map<String, Object> claims, String userName) {
		return Jwts.builder()
			.addClaims(claims)
			.setSubject(userName)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();

	}

	public Claims decode(@NotNull String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (SignatureException e) {
			throw new RuntimeException("Invalid token");
		}
	}

	public boolean validateToken(Claims claims, UserDetails userDetails) {
		final String username = claims.getSubject();
		final Date expiration = claims.getExpiration();
		return (username.equals(userDetails.getUsername()) && !expiration.before(new Date()));
	}
}
