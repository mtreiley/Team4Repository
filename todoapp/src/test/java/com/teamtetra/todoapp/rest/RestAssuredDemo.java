package com.teamtetra.todoapp.rest;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.teamtetra.todoapp.entity.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class RestAssuredDemo {

  // Make sure you save the random port the web server is running on in a variable
  @LocalServerPort private int port;

  @BeforeEach
  void setup() {
    // we can specify where we want all of our requests to be sent
    RestAssured.baseURI = "http://localhost";
    // we can specify the port for the requests
    RestAssured.port = port;
  }

  @Test
  void simpleRestAssuredTest() {

    // test data
    User testUser = new User();
    testUser.setUsername("Username");
    testUser.setPassword("P0ssword");

    /*
      Rest Assured tests have a three part struture that follows a given/when/then pattern.
      - given() = this method gives us the tools to put together our http request
      - when() = this method gives us access to the tools to actually make the request
      - then() = this method gives us access to the response and lets us validate it
    */

    // the given() let's us set up the headers, body, cookies, any sort of pre-conditions needed for
    // us to make the request
    given()
        .contentType(ContentType.JSON)
        .body(testUser)
        // the when() is where we decide what type of request we are actually making and the
        // endpoint we are hitting
        .when()
        .post("/register")
        // the then() is where we perform our validation
        .then()
        .statusCode(201);
  }

  @Test
  void failingTest() {
    // this will fail since user has no username or password for the registration
    User badCredentials = new User();
    given()
        .contentType(ContentType.JSON)
        .body(badCredentials)
        .when()
        .post("/register")
        .then()
        .statusCode(201);
  }
}