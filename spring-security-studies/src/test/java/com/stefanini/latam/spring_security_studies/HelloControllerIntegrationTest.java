package com.stefanini.latam.spring_security_studies;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

//A anotação @SpringBootTest é usada para testes de  integração em aplicativos Spring Boot.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//A anotação @TestContainer é uma anotação fornecida pela biblioteca Testcontainers, que é usada para gerenciar e configurar contêineres Docker durante os testes.
@Testcontainers
@AutoConfigureMockMvc
public class HelloControllerIntegrationTest {
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");
    @LocalServerPort
    private Integer port;

    //Antes de tudo irá inicializar o contêiner PostgreSQL para ser utilizado nos testes.
    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    //Após todos os testes irá derrubar o contêiner PostgreSQL. 
    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Autowired
    MockMvc mockMvc;


    //@DynamicPropertySource configura as propriedades do Spring Boot para usar o banco de dados PostgreSQL.
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    //Dentro do método setUp(), o RestAssured.baseURI é configurado para apontar para o endereço base da sua aplicação, utilizando a variável port que provavelmente representa a porta em que sua aplicação está sendo executada localmente. 
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    //connectionEstablished(): Método de teste que verifica se o contêiner do PostgreSQL está criado e em execução. Isso é útil para garantir que o ambiente de teste esteja configurado corretamente antes de executar os testes reais.
    @Test
    void connectionEstablished() {
        Assert.assertTrue(postgres.isCreated());
        Assert.assertTrue(postgres.isRunning());
    }

    @Test
    void shouldGetAllSuaEntidade() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get(RestAssured.baseURI.concat("/"))
        ).andExpect(
                MockMvcResultMatchers.status().isUnauthorized()
        );

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/")
                .then()
                .statusCode(401);
    }
}