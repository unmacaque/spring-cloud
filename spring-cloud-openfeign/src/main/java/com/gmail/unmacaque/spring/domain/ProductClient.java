package com.gmail.unmacaque.spring.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "catalog-provider", fallback = ProductClientFallback.class)
public interface ProductClient {

	@GetMapping("/products")
	List<Product> getProducts();

}
