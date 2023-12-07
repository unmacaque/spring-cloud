package com.gmail.unmacaque.spring.cloud.contract.producer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
abstract class ContractVerifierBase {

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	void before(RestDocumentationContextProvider restDocumentation) {
		RestAssuredMockMvc
				.mockMvc(MockMvcBuilders.webAppContextSetup(context)
						.apply(documentationConfiguration(restDocumentation))
						.alwaysDo(document("{class-name}_{method-name}"))
						.build());
	}
}
