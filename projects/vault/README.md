# spring-cloud-vault

Start a local [Vault](https://www.vaultproject.io/) dev server instance:

    docker run --rm -p 8200:8200 vault/vault:1.7.3

Copy the printed root token and set it as property `spring.cloud.vault.token` to this application.

Create a configuration key for this application using either the web UI or using the CLI tool:

    vault kv put secret/spring-cloud-vault foo=bar

Finally, start the application. Send a request to <http://localhost:8080> to show the secret retrieved from Vault.
