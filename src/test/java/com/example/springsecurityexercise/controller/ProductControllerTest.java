package com.example.springsecurityexercise.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.example.springsecurityexercise.application.ProductService;
import com.example.springsecurityexercise.config.SecurityConfig;

@WebMvcTest(ProductController.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ProductController.class, SecurityConfig.class})
class ProductControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserDetailsService userDetailsService;

	@MockBean
	private ProductService productService;

	@Test
	void getTest() throws Exception {
		mvc.perform(formLogin("/login").user("admin")
				.password("1111"))
			.andExpect(status().is3xxRedirection());
	}

	@Test
	void getAdminTest() throws Exception {
		mvc.perform(get("/products/all")
				.with(user("admin")
					.password("1111")
					.roles("ADMIN")))
			.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "1111", roles = "USER")
	void getUserTest() throws Exception {
		mvc.perform(get("/products/all"))
			.andExpect(status().isForbidden());
	}

}
