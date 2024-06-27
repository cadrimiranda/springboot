package com.dietreino.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		// new SpringApplicationBuilder().profiles(args[0]).sources(BackendApplication.class).run(args);
		SpringApplication.run(BackendApplication.class, args);
	}

}
