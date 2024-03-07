package com.example.booksreview.controller.integration;

import com.example.booksreview.configuration.TestContainersDatabaseConfiguration;
import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.dto.UserDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerIntegrationTest extends TestContainersDatabaseConfiguration {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static CreateUserDTO createUserDTO;
    private static UserDTO userDTO;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/user")
                .setPort(8888)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        createUserDTO = new CreateUserDTO("test@test.com", "Test Name", "strong-password");

    }

    @Test
    @DisplayName("Criar usuário com testes de integração deve retornar sucesso")
    void createUserWithIntegrationTestsShloudReturnSuccess() throws JsonProcessingException {
        String responseAsJson = RestAssured.given()
                .spec(specification)
                .contentType("application/json")
                .body(createUserDTO)
                .when().post().then().statusCode(201).extract().body().asString();

        userDTO = objectMapper.readValue(responseAsJson, UserDTO.class);

        Assertions.assertNotNull(userDTO);
        Assertions.assertNotNull(userDTO.guid());
        Assertions.assertNotNull(userDTO.name());
        Assertions.assertNotNull(userDTO.email());

        Assertions.assertEquals(createUserDTO.name(), userDTO.name());
        Assertions.assertEquals(createUserDTO.email(), userDTO.email());
    }


}
