package com.gmail.unmacaque.spring.cloud.contract.consumer.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureStubRunner(ids = ":spring-cloud-contract-producer")
@AutoConfigureMockMvc
class ConsumerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ConsumerController consumerController;

	@StubRunnerPort("spring-cloud-contract-producer")
	private int producerPort;

	@BeforeEach
	public void beforeEach() {
		this.consumerController.setPort(producerPort);
	}

	@Test
	void testContract() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						content().string("Hello World")
				);
	}
}
