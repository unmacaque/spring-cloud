package com.gmail.unmacaque.spring.cloud.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.wiremock.spring.EnableWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@AutoConfigureWebTestClient
@EnableWireMock
@ActiveProfiles("test")
class ApplicationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testRouteApi() {
		stubFor(get("/api").willReturn(aResponse().withBody("Api called!")));

		webTestClient
				.get()
				.uri("/api")
				.exchange()
				.expectStatus().isOk();

		verify(getRequestedFor(urlEqualTo("/api")));
	}

}
