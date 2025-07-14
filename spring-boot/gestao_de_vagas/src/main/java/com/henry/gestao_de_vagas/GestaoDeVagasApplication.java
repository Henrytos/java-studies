package com.henry.gestao_de_vagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @OpenAPIDefinition(info = @Info(title = "gestão de vagas api", description =
// "api de gestão de vagas do rh", version = "1.0.0"))
// @SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme =
// "bearer", bearerFormat = "JWT", in = SecuritySchemeIn.HEADER, description =
// "JWT Authorization header using the Bearer scheme. Example: \"Authorization:
// Bearer {token}\"")
public class GestaoDeVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeVagasApplication.class, args);
	}

}
