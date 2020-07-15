package com.application.caronavirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@SpringBootApplication
@EnableScheduling
public class CaronavirusTrackerApplication {

	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public Gson gson() {
		return new Gson();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CaronavirusTrackerApplication.class, args);
	}

}
