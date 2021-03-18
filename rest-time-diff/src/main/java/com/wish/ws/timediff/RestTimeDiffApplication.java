package com.wish.ws.timediff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class RestTimeDiffApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTimeDiffApplication.class, args);
	}
	@PostConstruct
	void started() {
		//TimeZone.setDefault(TimeZone.getTimeZone("Etc"));
	}
}
