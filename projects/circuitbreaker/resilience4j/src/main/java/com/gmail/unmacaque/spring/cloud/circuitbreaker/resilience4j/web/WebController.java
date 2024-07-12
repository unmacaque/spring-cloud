package com.gmail.unmacaque.spring.cloud.circuitbreaker.resilience4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@Autowired
	private CircuitBreakerFactory<?, ?> cbFactory;

	@GetMapping("/")
	public String hello(@RequestHeader(value = "X-Provoke-Fault", required = false) boolean fault) {
		return cbFactory
				.create("hello")
				.run(() -> doCall(fault), throwable -> "fallback");
	}

	private String doCall(boolean fault) {
		if (fault) {
			throw new RuntimeException("An internal error occured");
		}
		return "hello";
	}
}
