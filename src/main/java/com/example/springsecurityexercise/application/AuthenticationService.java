package com.example.springsecurityexercise.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurityexercise.config.UserInfoUserDetails;
import com.example.springsecurityexercise.domain.User;
import com.example.springsecurityexercise.infra.UserRepository;
import com.example.springsecurityexercise.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new UserInfoUserDetails(user);
	}

	public String login(String email, String password) {
		Map<String, Object> claims = new HashMap<>();
		Authentication authenticate = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(email, password));
		if (authenticate.isAuthenticated()) {
			return jwtUtil.encode(claims, email);
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}
}           
