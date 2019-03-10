# spring-cloud-sleuth

Run `mvn spring-boot:run`, then `curl http://localhost:8080` and observe the span and trace IDs in the structured JSON log.

Additionally, run a [Zipkin](https://github.com/openzipkin/docker-zipkin) server and open [http://localhost:9411](http://localhost:9411) in a browser to get a visual representation of the application traces.

```
docker run --rm -p 9411:9411 openzipkin/zipkin:2
```
