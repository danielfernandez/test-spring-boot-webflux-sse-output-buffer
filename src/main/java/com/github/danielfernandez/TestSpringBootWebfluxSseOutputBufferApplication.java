package com.github.danielfernandez;

import java.time.Duration;
import java.util.logging.Level;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
@RestController
public class TestSpringBootWebfluxSseOutputBufferApplication {


	@RequestMapping("/")
	public String main() {
		return "Use '/events' to get an SSE stream of events: curl -H \"text/event-stream\" http://localhost:8080/events";
	}


	@RequestMapping("/events")
	public Flux<String> events() {

		return Flux
				.interval(Duration.ofSeconds(1L))
				.map((i) -> String.format("%4d:%s",i,RandomStringUtils.randomAlphanumeric(95)))
				.log("events", Level.FINEST);

	}


	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootWebfluxSseOutputBufferApplication.class, args);
	}

}
