package com.example.springsecurityexercise.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurityexercise.application.ProductService;
import com.example.springsecurityexercise.config.UserInfoUserDetails;
import com.example.springsecurityexercise.domain.Product;

import lombok.RequiredArgsConstructor;

@RequestMapping("/products")
@RequiredArgsConstructor
@RestController
public class ProductController {
	private final ProductService productService;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is not secure";
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product> getAllTheProducts() {
		return productService.getProducts();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public Product getProductById(@PathVariable Long id, UserInfoUserDetails userDetails) {
		String email = userDetails.getUsername();
		return productService.getProduct(id);
	}
	
}
