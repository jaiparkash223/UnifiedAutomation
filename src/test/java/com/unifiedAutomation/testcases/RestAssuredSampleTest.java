package com.unifiedAutomation.testcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class RestAssuredSampleTest {

    @Test
    void testGetRequest() {
        // Sample GET request to JSONPlaceholder API
        Response response = RestAssured
                .given()
                    .baseUri("https://jsonplaceholder.typicode.com")
                    .basePath("/posts/1")
                    .contentType("application/json")
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("userId", equalTo(1))
                    .body("id", equalTo(1))
                    .body("title", notNullValue())
                    .extract()
                    .response();

        // Print the response
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.prettyPrint());
    }

    @Test
    void testPostRequest() {
        // Sample POST request to create a new post
        Response response = RestAssured
                .given()
                    .baseUri("https://jsonplaceholder.typicode.com")
                    .basePath("/posts")
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"title\": \"Test Post\",\n" +
                            "  \"body\": \"This is a test post created with RestAssured\",\n" +
                            "  \"userId\": 1\n" +
                            "}")
                .when()
                    .post()
                .then()
                    .statusCode(201)
                    .body("title", equalTo("Test Post"))
                    .body("userId", equalTo(1))
                    .extract()
                    .response();

        // Print the response
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.prettyPrint());
    }

    @Test
    void testGetRequestWithQueryParams() {
        // Sample GET request with query parameters
        Response response = RestAssured
                .given()
                    .baseUri("https://jsonplaceholder.typicode.com")
                    .basePath("/posts")
                    .queryParam("userId", 1)
                    .contentType("application/json")
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0))
                    .extract()
                    .response();

        // Print the response
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.prettyPrint());
    }
}