package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@FunctionalSpringBootTest
class FunctionalApplicationTest {

	@Autowired
	private FunctionCatalog catalog;

	@Test
	void helloFunctionTest() {
		Supplier<String> function = catalog.lookup(Supplier.class, "hello");
		assertThat(function.get()).isEqualTo("Hello World");
	}

	@Test
	void printFunctionTest() {
		Consumer<String> function = catalog.lookup(Supplier.class, "print");
		assertThatCode(() -> function.accept("Hello")).doesNotThrowAnyException();
	}

	@Test
	void uppercaseFunctionTest() {
		Function<String, String> function = catalog.lookup(Function.class, "uppercase");
		assertThat(function.apply("it works")).isEqualTo("IT WORKS");
	}
}
