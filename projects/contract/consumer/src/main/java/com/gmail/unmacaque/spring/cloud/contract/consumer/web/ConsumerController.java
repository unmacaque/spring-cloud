package com.gmail.unmacaque.spring.cloud.contract.consumer.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class ConsumerController {

	private final RestClient restClient;

	public ConsumerController(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.build();
	}

	@GetMapping("/")
	public String consume() {
		return restClient
				.get()
				.uri("/hello")
				.retrieve()
				.body(String.class) + " World";
	}

}
