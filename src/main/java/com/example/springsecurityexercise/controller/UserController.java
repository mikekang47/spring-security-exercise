package com.example.springsecurityexercise.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurityexercise.application.UserService;
import com.example.springsecurityexercise.domain.User;
import com.example.springsecurityexercise.dto.UserCreateRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public String createUser(@RequestBody UserCreateRequest userCreate) {
		User user = userService.createUser(userCreate);
		return user.toString();
	}
}
