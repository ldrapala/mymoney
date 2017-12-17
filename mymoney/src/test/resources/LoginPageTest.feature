Feature: This feature should check correct login procedure

  Scenario: Login with correct data
    Given login form
    When we login with valid data
    Then we should see account page
