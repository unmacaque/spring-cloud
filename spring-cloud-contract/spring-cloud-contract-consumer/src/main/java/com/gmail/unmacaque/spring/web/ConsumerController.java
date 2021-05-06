package com.gmail.unmacaque.spring.web;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class ConsumerController {

	private int port;

	private final RestTemplate restTemplate;

	public ConsumerController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@GetMapping("/")
	public String consume() {
		final var url = URI.create("http://localhost:" + port + "/hello");
		return restTemplate.getForObject(url, String.class) + " World";
	}

	public void setPort(int port) {
		this.port = port;
	}
}
