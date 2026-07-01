Feature: Create Todo

    Background: The user is logged in and on the dashboard
        Given   The user is logged in with valid credentials
        Then    The user should be navigated to the dashboard

    Scenario: User can create a todo with a valid title
        When    The user enters "Buy groceries" in the todo title field
        And     The user clicks the Add todo button
        Then    The todo "Buy groceries" should appear in the todo list

    Scenario Outline: User cannot create a todo with a duplicate title
        When    The user enters "<title>" in the todo title field
        And     The user clicks the Add todo button
        Then    The user should see a todo error message "<message>"

    Examples:
    |title|message|
    |Buy groceries|Todo title must be unique|
