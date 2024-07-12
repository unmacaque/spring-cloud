package com.gmail.unmacaque.spring.cloud.function.rsocket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Function;

@FunctionalSpringBootTest
class FunctionalApplicationTest {

	@Autowired
	private FunctionCatalog catalog;

	@Test
	void lowercaseFunctionTest() {
		final Function<Flux<String>, Mono<String>> function = catalog.lookup(Function.class, "lowercase");
		StepVerifier.create(function.apply(Flux.just("IT WORKS")))
				.expectNext("it works")
				.verifyComplete();
	}

	@Test
	void uppercaseFunctionTest() {
		final Function<Flux<String>, Mono<String>> function = catalog.lookup(Function.class, "uppercase");
		StepVerifier.create(function.apply(Flux.just("it works")))
				.expectNext("IT WORKS")
				.verifyComplete();
	}
}
