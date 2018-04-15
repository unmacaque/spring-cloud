package com.gmail.unmacaque.spring.domain;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "catalog-provider", fallback = ProductClientFallback.class)
public interface ProductClient {

	@GetMapping("/products")
	List<Product> getProducts();

}
