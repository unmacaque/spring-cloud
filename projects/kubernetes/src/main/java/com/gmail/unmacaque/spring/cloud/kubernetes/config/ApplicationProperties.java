package com.gmail.unmacaque.spring.cloud.kubernetes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("app")
public record ApplicationProperties(Map<String, String> keys) {}
