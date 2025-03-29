package com.alic3.versioned;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class VersionedApplication {

	public static void main(String[] args) {
		SpringApplication.run(VersionedApplication.class, args);
	}

}