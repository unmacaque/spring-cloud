package com.gmail.unmacaque.spring.cloud.circuitbreaker.resilience4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testHello() throws Exception {
		mvc.perform(get("/"))
				.andExpect(content().string("hello"));
	}

	@Test
	void testHelloWithFault() throws Exception {
		mvc.perform(get("/")
						.header("X-Provoke-Fault", "1"))
				.andExpect(content().string("fallback"));
	}

}
