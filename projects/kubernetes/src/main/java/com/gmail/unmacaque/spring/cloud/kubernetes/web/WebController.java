package com.gmail.unmacaque.spring.cloud.kubernetes.web;

import com.gmail.unmacaque.spring.cloud.kubernetes.config.ApplicationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WebController {

	private final ApplicationProperties properties;

	public WebController(ApplicationProperties properties) {
		this.properties = properties;
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> index() {
		return properties.keys();
	}
}
