package com.example.springsecurityexercise.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.springsecurityexercise.config.UserInfoUserDetails;
import com.example.springsecurityexercise.domain.User;
import com.example.springsecurityexercise.infra.UserRepository;


@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new UserInfoUserDetails(user);
	}
}
