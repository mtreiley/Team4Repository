package com.teamtetra.todoapp.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.teamtetra.todoapp.entity.User;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Steps {

    private User testUser;
    private Response response;

    @Before
    public void setup() {
        // Point RestAssured at the running Spring Boot test server.
        // The Cucumber suite does not spin up its own server, so we target
        // the default dev port. For a fully isolated run, wire this through
        // a shared Spring context instead.
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Given("The user is on the login page")
    public void theUserIsOnTheLoginPage() {
        // No HTTP action needed — this step sets up the test scenario context.
        testUser = new User();
    }

    @When("The user clicks the registration link")
    public void theUserClicksTheRegistrationLink() {
        // No HTTP action needed — navigating to registration is a UI concern.
    }

    @And("The user enters valid crendentials")
    public void theUserEntersValidCredentials() {
        testUser.setUsername("CucmbrUsr");
        testUser.setPassword("P0ssword!");
    }

    @And("The user clicks the register button")
    public void theUserClicksTheRegisterButton() {
        response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(testUser)
            .when()
                .post("/register");
    }

    @Then("The user should see a success message")
    public void theUserShouldSeeASuccessMessage() {
        assertEquals(201, response.getStatusCode(),
            "Expected 201 CREATED but got: " + response.getStatusCode()
            + " — body: " + response.getBody().asString());
    }
}
