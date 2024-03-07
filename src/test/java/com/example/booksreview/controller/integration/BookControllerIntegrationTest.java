package com.example.booksreview.controller.integration;

import com.example.booksreview.configuration.TestContainersDatabaseConfiguration;
import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.model.Book;
import com.example.booksreview.model.User;

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

import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookControllerIntegrationTest extends TestContainersDatabaseConfiguration {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static CreateBookDTO createBookDTO;
    private static CreateUserDTO createUserDTO;
    public static BookDTO bookDTO;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/book")
                .setPort(8888)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }


    @Test
    @Order(1)
    @DisplayName("Should create book with integration test success")
    void createBookWithIntegrationTestsShouldReturnSuccess() throws JsonProcessingException {

        CreateBookDTO createBookDTO = new CreateBookDTO("Book Title Test", 2024);

        String responseAsJson = RestAssured.given()
                .spec(specification)
                .contentType("application/json")
                .body(createBookDTO)
                .when().post().then().statusCode(201).extract().body().asString();

        BookDTO bookDTO = objectMapper.readValue(responseAsJson, BookDTO.class);

        Assertions.assertNotNull(bookDTO);
        Assertions.assertNotNull(bookDTO.title());
        Assertions.assertNotNull(bookDTO.year());

        Assertions.assertEquals(createBookDTO.title(), bookDTO.title());
        Assertions.assertEquals(createBookDTO.year(), bookDTO.year());
    }

    @Test
    @DisplayName("Buscar livro por ID retorna com sucesso")
    void getBookByIntegrationTestShouldReturnSuccess() throws JsonProcessingException {

        Long id = (Long) 1L;
        String title = "The Great Gatsby";
        Integer year = 1925;

        String email = "user1@example.com";
        String password = "password123";


        String responseAsJson = RestAssured.given()
                .spec(specification)
                .pathParam("id", id)
                .when().get("{id}").then().statusCode(200).extract().body().asString();

        BookDTO bookById = objectMapper.readValue(responseAsJson, BookDTO.class);

        Assertions.assertNotNull(bookById);
        Assertions.assertNotNull(bookById.id());
        Assertions.assertNotNull(bookById.title());
        Assertions.assertNotNull(bookById.year());

        Assertions.assertEquals(title, bookById.title());
        Assertions.assertEquals(year, bookById.year());
    }


}
