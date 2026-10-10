package com.pup.taguig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class SpringBootDitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDitApplication.class, args);
		
		User user = new User();
		user.code();
	}
}
