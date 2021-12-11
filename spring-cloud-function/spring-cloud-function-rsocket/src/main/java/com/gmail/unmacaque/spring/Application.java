package com.gmail.unmacaque.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Function<String, String> lowercase() {
		return String::toLowerCase;
	}

	@Bean
	public Function<String, String> uppercase() {
		return String::toUpperCase;
	}
}
