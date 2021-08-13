package com.gmail.unmacaque.spring.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

	@Value("${foo}")
	private String secret;

	@GetMapping("/")
	public String hello() {
		return secret;
	}
}
