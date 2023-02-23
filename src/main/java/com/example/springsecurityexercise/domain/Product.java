package com.example.springsecurityexercise.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int quantity;

	private int price;
}
