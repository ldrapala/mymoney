Feature: This feature should check invalid login

  Scenario Outline: Login with improper credentials
    Given login page form
    When we login with invalid data (<email> and <password>)
    Then we should see error message

     Examples:
          |     email       |   password   |
          | "email1@com.pl" |  "password1" |
          | "email2@com.pl" |  "password2" |
