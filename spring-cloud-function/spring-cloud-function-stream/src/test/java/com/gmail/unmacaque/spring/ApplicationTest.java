package com.gmail.unmacaque.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@FunctionalSpringBootTest
public class ApplicationTest {

	@Autowired
	private Processor processor;

	@Autowired
	private MessageCollector messageCollector;

	@Test
	public void messageIsProcessed() {
		processor.input().send(MessageBuilder.withPayload("it works").build());
		Message<?> received = messageCollector.forChannel(processor.output()).poll();
		assertThat(received.getPayload()).isEqualTo("IT WORKS");
	}

}
