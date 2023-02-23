package com.example.springsecurityexercise.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurityexercise.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
