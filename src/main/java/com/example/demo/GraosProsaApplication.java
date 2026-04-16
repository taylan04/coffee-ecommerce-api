package com.example.demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class GraosProsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraosProsaApplication.class, args);
	}

}
