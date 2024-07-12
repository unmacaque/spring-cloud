package com.gmail.unmacaque.spring.cloud.consul.client.web;

import com.gmail.unmacaque.spring.cloud.consul.client.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ClientController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/products")
	public List<Product> getProducts() {
		final var typeReference = new ParameterizedTypeReference<List<Product>>() {};
		final var url = "http://spring-cloud-consul-server/products";
		return restTemplate.exchange(url, HttpMethod.GET, null, typeReference).getBody();
	}

	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable long productId) {
		final var url = "http://spring-cloud-consul-server/products/{productId}";
		return restTemplate.getForObject(url, Product.class, productId);
	}
}
