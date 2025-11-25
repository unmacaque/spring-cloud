package com.gmail.unmacaque.spring.cloud.contract.consumer.config;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "client")
@Validated
public record RestClientProperties(@NotEmpty String baseUrl) {}
