package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalSpringBootTest
class FunctionalApplicationTest {

	@Autowired
	private FunctionCatalog catalog;

	@Test
	void helloFunctionTest() {
		final Supplier<Mono<String>> function = catalog.lookup(Supplier.class, "hello");
		StepVerifier.create(function.get())
				.expectNext("Hello World")
				.verifyComplete();
	}

	@Test
	void printFunctionTest() {
		final Consumer<Flux<String>> function = catalog.lookup(Function.class, "print");
		StepVerifier.create(Flux.just(Flux.just("it works")).doOnNext(function))
				.expectNextCount(1)
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
