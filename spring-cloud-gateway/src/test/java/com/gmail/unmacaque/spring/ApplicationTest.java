package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"endpoint=http://localhost:${wiremock.server.port}"})
@AutoConfigureWireMock(port = 0)
class ApplicationTest {

	@LocalServerPort
	private int port;

	@Test
	void testRouteApi() {
		stubFor(get("/api").willReturn(aResponse().withBody("Api called!")));

		WebTestClient
				.bindToServer()
				.baseUrl("http://localhost:" + port)
				.build()
				.get()
				.uri("/api")
				.exchange()
				.expectStatus().isOk();

		verify(getRequestedFor(urlEqualTo("/api")));
	}

}
