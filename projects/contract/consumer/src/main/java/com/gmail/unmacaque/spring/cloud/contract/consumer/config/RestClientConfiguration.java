package com.gmail.unmacaque.spring.cloud.contract.consumer.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RestClientProperties.class)
class RestClientConfiguration {

	@Bean
	public RestClientCustomizer restClientCustomizer(RestClientProperties properties) {
		return builder -> builder.baseUrl(properties.baseUrl());
	}

}
