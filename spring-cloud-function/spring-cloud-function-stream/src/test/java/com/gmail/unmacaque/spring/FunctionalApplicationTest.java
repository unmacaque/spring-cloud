package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@FunctionalSpringBootTest
class FunctionalApplicationTest {

	@Autowired
	private FunctionCatalog catalog;

	@Test
	void functionTest() {
		final Function<String, String> function = catalog.lookup(Function.class, "uppercase");
		assertThat(function.apply("it works")).isEqualTo("IT WORKS");
	}
}
