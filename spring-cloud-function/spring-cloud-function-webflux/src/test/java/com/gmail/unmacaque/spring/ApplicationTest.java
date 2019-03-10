package com.gmail.unmacaque.spring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@RunWith(SpringRunner.class)
@FunctionalSpringBootTest
@AutoConfigureRestDocs
@AutoConfigureWebTestClient
public class ApplicationTest {

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Autowired
	private WebTestClient client;

	@Test
	public void getSupplier() throws Exception {
		client.get()
				.uri("/hello")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Hello World")
				.consumeWith(document("{method-name}"));
	}

	@Test
	public void postConsumer() throws Exception {
		client.post()
				.uri("/print")
				.contentType(MediaType.TEXT_PLAIN)
				.syncBody("Spring Cloud Function Web")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.consumeWith(document("{method-name}"));
	}

	@Test
	public void postFunction() throws Exception {
		client.post()
				.uri("/uppercase")
				.contentType(MediaType.TEXT_PLAIN)
				.syncBody("Spring Cloud Function Web")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("SPRING CLOUD FUNCTION WEB")
				.consumeWith(document("{method-name}"));
	}

}
