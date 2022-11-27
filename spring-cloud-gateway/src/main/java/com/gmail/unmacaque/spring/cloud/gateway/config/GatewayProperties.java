package com.gmail.unmacaque.spring.cloud.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class GatewayProperties {
	private String endpoint = "http://localhost:8888";

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
}
