# spring-cloud-contract

Contracts are located in `spring-cloud-contract-producer/src/contractTest/resources/contracts`.

Run tests in `spring-cloud-contract-producer` to verify that producer tests is fulfilling the contract.

Run the `verifierStubsJar` task for `spring-cloud-contract-producer` to produce a JAR file with the contracts.

Run tests in `spring-cloud-contract-consumer` to test against a producer stub.
