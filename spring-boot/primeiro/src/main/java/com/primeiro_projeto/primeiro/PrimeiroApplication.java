package com.primeiro_projeto.primeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
// @ComponentScan(basePackageClasses = PrimeiroController.class)
@ComponentScan(basePackages = "com.primeiro_projeto")
public class PrimeiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroApplication.class, args);
	}

}
