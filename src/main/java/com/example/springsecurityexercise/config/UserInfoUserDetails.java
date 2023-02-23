package com.example.springsecurityexercise.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.springsecurityexercise.domain.User;

import lombok.Getter;

@Getter
public class UserInfoUserDetails implements UserDetails {
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfoUserDetails(User user) {
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.authorities = Stream.of(user.getRoles()).map(r -> new SimpleGrantedAuthority(r.toString()))
			.collect(Collectors.toList());
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
