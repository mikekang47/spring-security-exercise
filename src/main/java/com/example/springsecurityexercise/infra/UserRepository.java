package com.example.springsecurityexercise.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurityexercise.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
