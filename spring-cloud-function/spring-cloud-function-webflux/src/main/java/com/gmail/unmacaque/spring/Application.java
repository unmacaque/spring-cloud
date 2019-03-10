package com.gmail.unmacaque.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Supplier<String> hello() {
		return () -> "Hello World";
	}

	@Bean
	public Consumer<String> print() {
		return System.out::println;
	}

	@Bean
	public Function<String, String> uppercase() {
		return String::toUpperCase;
	}
}
