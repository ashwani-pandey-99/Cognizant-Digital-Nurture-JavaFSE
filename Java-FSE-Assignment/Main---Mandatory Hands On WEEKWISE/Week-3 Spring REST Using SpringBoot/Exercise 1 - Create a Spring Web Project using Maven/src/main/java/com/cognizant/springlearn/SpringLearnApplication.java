package com.cognizant.springlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearnApplication {

	public static void main(String[] args) {
		System.out.println("Spring Boot Application Started");
		SpringApplication.run(SpringLearnApplication.class, args);
		System.out.println("Application Running Successfully");
	}

}