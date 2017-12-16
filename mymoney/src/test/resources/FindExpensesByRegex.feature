Feature: This test should find an Expenses by given regex

  Scenario: Find proper Expenses by regex
    Given there is one expenses
    And another one with different category
    When beginning of his category is passed as "To.."
    Then we should find this expenses
    But I should not see more than one expenses
