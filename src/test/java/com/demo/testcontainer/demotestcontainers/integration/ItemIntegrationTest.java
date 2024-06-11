package com.demo.testcontainer.demotestcontainers.integration;

import com.demo.testcontainer.demotestcontainers.model.ItemRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ItemIntegrationTest {


    @Container
    private static final OracleContainer oracleContainer = new OracleContainer("gvenzl/oracle-free:slim-faststart")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("test");

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
        registry.add("spring.datasource.username", oracleContainer::getUsername);
        registry.add("spring.datasource.password", oracleContainer::getPassword);
    }

    @BeforeAll
    public static void beforeAll() {

    }

    @Test
    void test_create_item_and_get_item() {
        String itemId = "test-id-1";
        String itemName = "test-name-1";

        ItemRequest itemRequest = new ItemRequest(itemId, itemName);

        given()
                .contentType(ContentType.JSON)
                .body(itemRequest)
                .when()
                .post("/items/item")
                .then()
                .statusCode(HttpStatus.OK.value());

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/items/{id}", itemId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("id", equalTo(itemId))
                .body("name", equalTo(itemName));
    }

}
