Feature: Todo App Login

    Background: All users already have a registed account
        Given   The user has an existing account

    Scenario: Users can login with valid credentials
        When    The user enters valid crendentials
        And     The user clicks the login button
        Then    The user should be navigated to the dashboard


    Scenario Outline: Users can not login with invalid credentials
        When    The user enters username "<username>" and password "<password>"
        And     The user clicks the login button
        Then    The user should see failure message "<message>"


    Examples:
    |username|password|message|
    |IncorrectUser|P0ssword|Incorrect login crendentials|
    |Username|Inc0rrectPass|Incorrect login crendentials|