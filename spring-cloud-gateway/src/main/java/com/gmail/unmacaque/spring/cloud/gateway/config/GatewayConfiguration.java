package com.gmail.unmacaque.spring.cloud.gateway.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(GatewayProperties.class)
public class GatewayConfiguration {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder, GatewayProperties properties) {
		return builder.routes()
				.route("api", p -> p.path("/api").uri(properties.endpoint()))
				.build();
	}

}
