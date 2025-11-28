package com.gmail.unmacaque.spring.cloud.function.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class Application {

	static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	Function<String, String> uppercase() {
		return String::toUpperCase;
	}
}
