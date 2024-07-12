package com.gmail.unmacaque.spring.cloud.consul.client;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.consul.ConsulContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Tag("testcontainers")
class ConsulIT {

	@Container
	private static final ConsulContainer consulContainer = new ConsulContainer("consul:latest");

	@Test
	void testContextLoads() {}

}
