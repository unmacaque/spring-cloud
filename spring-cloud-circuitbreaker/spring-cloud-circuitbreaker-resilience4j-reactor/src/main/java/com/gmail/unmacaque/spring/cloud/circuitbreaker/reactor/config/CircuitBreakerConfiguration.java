package com.gmail.unmacaque.spring.cloud.circuitbreaker.reactor.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration(proxyBeanMethods = false)
public class CircuitBreakerConfiguration {

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> customizer() {
		return factory -> factory
				.configureDefault(id -> new Resilience4JConfigBuilder(id)
						.circuitBreakerConfig(CircuitBreakerConfig
								.custom()
								.failureRateThreshold(20)
								.waitDurationInOpenState(Duration.ofSeconds(10))
								.slidingWindowSize(10)
								.build()
						)
						.timeLimiterConfig(TimeLimiterConfig
								.custom()
								.timeoutDuration(Duration.ofSeconds(1))
								.build())
						.build()
				);
	}

}
