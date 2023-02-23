package com.example.springsecurityexercise.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurityexercise.application.AuthenticationService;
import com.example.springsecurityexercise.dto.SessionRequest;

import lombok.RequiredArgsConstructor;

@RequestMapping("/session")
@RestController
@RequiredArgsConstructor
public class SessionController {
	private final AuthenticationService authenticationService;

	@PostMapping("/login")
	public String login(@RequestBody SessionRequest sessionRequest) {
 		return authenticationService.login(sessionRequest.getEmail(), sessionRequest.getPassword());
	}
}
