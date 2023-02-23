package com.example.springsecurityexercise.application;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
                                                                                    
import org.springframework.stereotype.Service;

import com.example.springsecurityexercise.domain.Product;
import com.example.springsecurityexercise.infra.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	@PostConstruct
	public void loadProductsFromDB() {
		for (long i = 1; i <= 100; i++) {
			productRepository.save(
				new Product(i, "Product " + i,
					new Random().nextInt(10),
					new Random().nextInt(5000)));
		}
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public Product getProduct(Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Product not found"));
	}
}
