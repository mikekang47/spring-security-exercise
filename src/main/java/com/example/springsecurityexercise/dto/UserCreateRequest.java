package com.example.springsecurityexercise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCreateRequest {
	private String name;
	private String password;
	private String email;
}
