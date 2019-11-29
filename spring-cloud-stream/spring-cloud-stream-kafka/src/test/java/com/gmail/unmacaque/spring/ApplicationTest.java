package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest {

	@Autowired
	private Processor processor;

	@Autowired
	private MessageCollector messageCollector;

	@Test
	void messageIsProcessed() {
		processor.input().send(MessageBuilder.withPayload("it works").build());
		Message<?> received = messageCollector.forChannel(processor.output()).poll();
		assertThat(received.getPayload()).isEqualTo("IT WORKS");
	}

}
