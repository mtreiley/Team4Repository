Feature: Todo App Registration
    Scenario: Users can register with valid credentials
        Given   The user is on the login page
        When    The user clicks the registration link
        And     The user enters valid crendentials
        And     The user clicks the register button
        Then    The user should see a success message