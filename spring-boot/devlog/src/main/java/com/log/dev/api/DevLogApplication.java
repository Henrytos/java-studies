package com.log.dev.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(description = "api de blog de programadores e desenvolvedores", summary = "api v1", version = "1.0", title = "DevLog Api"))
public class DevLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevLogApplication.class, args);
	}

}
