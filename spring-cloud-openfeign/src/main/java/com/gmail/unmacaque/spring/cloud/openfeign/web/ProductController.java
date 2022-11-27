package com.gmail.unmacaque.spring.cloud.openfeign.web;

import com.gmail.unmacaque.spring.cloud.openfeign.domain.Product;
import com.gmail.unmacaque.spring.cloud.openfeign.domain.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	private ProductClient productClient;

	@GetMapping("/products")
	public List<Product> products() {
		return productClient.getProducts();
	}
}
