package com.gmail.unmacaque.spring.cloud.consul.server.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ServerControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetProducts() throws Exception {
		mvc.perform(get("/products"))
				.andExpectAll(
						status().isOk(),
						jsonPath("$.length()").value(1)
				);
	}

	@Test
	void testGetProductById() throws Exception {
		mvc.perform(get("/products/1"))
				.andExpectAll(
						status().isOk(),
						jsonPath("$.productId").value("1")
				);
	}

	@Test
	void testGetProductNotFound() throws Exception {
		mvc.perform(get("/products/100"))
				.andExpect(status().isNotFound());
	}
}
