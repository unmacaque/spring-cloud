package com.gmail.unmacaque.spring.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class StringProcessor {

	private static final Logger log = LoggerFactory.getLogger(StringProcessor.class);

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Flux<String> transform(Flux<String> input) {
		return input
				.doOnNext(log::info)
				.map(String::toUpperCase);
	}
}
