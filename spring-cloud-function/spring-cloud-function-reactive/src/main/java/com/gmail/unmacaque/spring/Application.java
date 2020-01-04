package com.gmail.unmacaque.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Supplier<Mono<String>> hello() {
		return () -> Mono.just("Hello World");
	}

	@Bean
	public Consumer<Flux<String>> print() {
		return flux -> flux.subscribe(System.out::println);
	}

	@Bean
	public Function<Flux<String>, Mono<String>> uppercase() {
		return flux -> flux.next().map(String::toUpperCase);
	}
}
