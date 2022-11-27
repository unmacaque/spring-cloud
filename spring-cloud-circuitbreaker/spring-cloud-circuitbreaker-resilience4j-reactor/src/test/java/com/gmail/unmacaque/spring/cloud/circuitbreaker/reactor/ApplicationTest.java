package com.gmail.unmacaque.spring.cloud.circuitbreaker.reactor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class ApplicationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testHello() {
		webTestClient
				.get()
				.uri("/")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("hello");
	}

	@Test
	void testHelloWithFault() {
		webTestClient
				.get()
				.uri("/")
				.header("X-Provoke-Fault", "1")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("fallback");
	}

}
