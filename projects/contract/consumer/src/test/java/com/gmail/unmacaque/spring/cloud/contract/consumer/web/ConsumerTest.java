package com.gmail.unmacaque.spring.cloud.contract.consumer.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureStubRunner(ids = ":producer")
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ConsumerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testContract() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						content().string("Hello World")
				);
	}
}
