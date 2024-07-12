package com.gmail.unmacaque.spring.cloud.function.webflux;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@FunctionalSpringBootTest
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@AutoConfigureWebTestClient
class ApplicationTest {

	@Autowired
	private WebTestClient client;

	@Test
	void getSupplier() {
		client.get()
				.uri("/hello")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Hello World")
				.consumeWith(document("{method-name}"));
	}

	@Test
	void postConsumer() {
		client.post()
				.uri("/print")
				.contentType(MediaType.TEXT_PLAIN)
				.bodyValue("Spring Cloud Function Web")
				.exchange()
				.expectStatus().isAccepted()
				.expectBody()
				.consumeWith(document("{method-name}"));
	}

	@Test
	void postFunction() {
		client.post()
				.uri("/uppercase")
				.contentType(MediaType.TEXT_PLAIN)
				.accept(MediaType.TEXT_PLAIN)
				.bodyValue("Spring Cloud Function Web")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("SPRING CLOUD FUNCTION WEB")
				.consumeWith(document("{method-name}"));
	}

}
