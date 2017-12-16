Feature: This feature should delete given expenses from db
    
  Scenario Outline: Delete expenses from db
    Given we add new expenses with <amount>, <category>, <date>, <details>
    When we delete new expenses
    Then new expenses should not be visible in db
    But the previous data should stay untouchable

     Examples:
          | amount |   category    |    date    |   details     |
          |  20000 |    "toys"     | 2017-10-25 | "lego bricks" |
          |  50000 | "electronics" | 2017-12-15 |    "monitor"  |
