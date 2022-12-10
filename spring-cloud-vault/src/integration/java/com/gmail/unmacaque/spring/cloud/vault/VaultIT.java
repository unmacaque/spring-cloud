package com.gmail.unmacaque.spring.cloud.vault;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.vault.VaultContainer;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@Tag("testcontainers")
class VaultIT {

	private static final String VAULT_TOKEN = UUID.randomUUID().toString();

	@Container
	private static final VaultContainer<?> vaultContainer = new VaultContainer<>("vault:latest")
			.withVaultToken(VAULT_TOKEN)
			.withSecretInVault("secret/spring-cloud-vault", "foo=test");

	@Test
	void testGet() throws Exception {
		final String[] args = {
				"--spring.cloud.vault.port=" + vaultContainer.getFirstMappedPort(),
				"--spring.cloud.vault.token=" + VAULT_TOKEN
		};

		try (final var context = SpringApplication.run(Application.class, args)) {
			final var mvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) context).build();
			mvc.perform(get("/"))
					.andExpectAll(
							status().isOk(),
							content().string("test")
					);
		}
	}

}
