package com.gmail.unmacaque.spring.cloud.circuitbreaker.reactor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WebController {

	@Autowired
	private ReactiveCircuitBreakerFactory<?, ?> cbFactory;

	@GetMapping("/")
	public Mono<String> hello(@RequestHeader(value = "X-Provoke-Fault", required = false) boolean fault) {
		return doCall(fault)
				.transform(it -> cbFactory
						.create("hello")
						.run(it, throwable -> Mono.just("fallback"))
				);
	}

	private Mono<String> doCall(boolean fault) {
		if (fault) {
			return Mono.error(() -> new RuntimeException("An internal error occured"));
		}
		return Mono.just("hello");
	}
}
