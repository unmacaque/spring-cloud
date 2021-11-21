package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.Product;
import com.gmail.unmacaque.spring.domain.ProductClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public record ProductController(ProductClient productClient) {

	@GetMapping("/products")
	public List<Product> products() {
		return productClient.getProducts();
	}
}
