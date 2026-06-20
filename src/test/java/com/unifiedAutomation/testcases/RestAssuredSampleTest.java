package com.unifiedAutomation.testcases;

import com.unifiedAutomation.utils.PropertiesLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.Matchers.*;

public class RestAssuredSampleTest {
    static Map<String, Properties> propertiesMap;

    @BeforeAll
    public static void init() {
        propertiesMap = PropertiesLoader.load();
    }

    @Test
    void testGetRequest() {
        // Sample GET request to JSONPlaceholder API
        Response response = RestAssured
                .given()
                    .baseUri(propertiesMap.get("RestConfig").getProperty("hostname"))
                    .basePath("/posts/1")
                    .contentType(propertiesMap.get("RestConfig").getProperty("contentType"))
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
        File jsonFile = new File("src/test/resources/json/post_request_body.json");
        // Sample POST request to create a new post
        Response response = RestAssured
                .given()
                    .baseUri(propertiesMap.get("RestConfig").getProperty("hostname"))
                    .basePath(propertiesMap.get("RestConfig").getProperty("postsEndpoint"))
                    .contentType(propertiesMap.get("RestConfig").getProperty("contentType"))
                    .body(jsonFile)
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
                    .baseUri(propertiesMap.get("RestConfig").getProperty("hostname"))
                    .basePath("/posts")
                    .queryParam("userId", 1)
                    .contentType(propertiesMap.get("RestConfig").getProperty("contentType"))
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