package com.example.springsecurityexercise.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springsecurityexercise.dto.UserCreateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String name;
	private String password;
	@Enumerated(EnumType.STRING)
	private RoleType roles;

	public User(String email, String name, String password, RoleType roles) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public static User createUser(UserCreateRequest userRequest, PasswordEncoder passwordEncoder) {
		return new User(userRequest.getEmail(), userRequest.getName(),
			passwordEncoder.encode(userRequest.getPassword()), RoleType.ROLE_USER);
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", email='" + email + '\'' +
			", name='" + name + '\'' +
			", password='" + password + '\'' +
			", roles=" + roles +
			'}';
	}
}
