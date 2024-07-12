package com.gmail.unmacaque.spring.cloud.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("gateway")
public record GatewayProperties(@DefaultValue("http://localhost:8888") String endpoint) {}
