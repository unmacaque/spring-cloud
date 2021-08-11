package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ServerController {

	private final List<Product> products;

	public ServerController() {
		this.products = List.of(createProduct());
	}

	@GetMapping("/products")
	public List<Product> getProducts() {
		return products;
	}

	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable long productId) {
		return products
				.stream()
				.filter(product -> product.getProductId() == productId)
				.findFirst()
				.orElseThrow();
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException() {
		return ResponseEntity.notFound().build();
	}

	private Product createProduct() {
		final var product = new Product();
		product.setProductId(1);
		product.setName("Samsung Smart TV");
		product.setDescription("Watch TV in brilliant quality");
		return product;
	}
}
