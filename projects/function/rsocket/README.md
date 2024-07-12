# spring-cloud-function-rsocket

Start the application.

Download the [RSocket Client CLI (RSC)](https://github.com/making/rsc).

Make a request with the command below:

    java -jar rsc-0.9.1.jar --debug --request --data "Hello" --route uppercase tcp://localhost:9898
