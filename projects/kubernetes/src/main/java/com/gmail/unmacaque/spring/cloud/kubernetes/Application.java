package com.gmail.unmacaque.spring.cloud.kubernetes;

import com.gmail.unmacaque.spring.cloud.kubernetes.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {

	static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
