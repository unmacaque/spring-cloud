package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.MessageBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class ApplicationTest {

	@Autowired
	private InputDestination input;

	@Autowired
	private OutputDestination output;

	@Test
	void messageIsProcessed() {
		input.send(MessageBuilder.withPayload("it works").build());
		final String message = new String(output.receive().getPayload());
		assertThat(message).isEqualTo("IT WORKS");
	}

}
