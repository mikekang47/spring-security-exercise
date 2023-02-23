package com.example.springsecurityexercise.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springsecurityexercise.domain.User;
import com.example.springsecurityexercise.dto.UserCreateRequest;
import com.example.springsecurityexercise.infra.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User createUser(UserCreateRequest userCreate) {
		User user = User.createUser(userCreate, passwordEncoder);
		return userRepository.save(user);
	}
}
