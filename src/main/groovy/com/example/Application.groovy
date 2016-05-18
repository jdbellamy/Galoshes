package com.example

import com.example.dao.GreetingRepository
import com.example.services.GreetingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages=['com.example'])
class Application implements CommandLineRunner {

	@Autowired GreetingService greetingService

	@Autowired private GreetingRepository repository

	@Override
	public void run(String... args) {
	}

	static void main(String[] args) {
		SpringApplication.run Application, args
	}
}
