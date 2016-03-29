package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DemoApplication implements CommandLineRunner {

	@Autowired GreetingService greetingService
	@Autowired OtherService otherService

	@Override
	public void run(String... args) {
		//this.greetingService.callHello()
		//this.otherService.getDoubles(1)
	}

	static void main(String[] args) {
		SpringApplication.run DemoApplication, args
	}
}
