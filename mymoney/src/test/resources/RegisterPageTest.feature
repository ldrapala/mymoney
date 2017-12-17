Feature: This feature should register an user
    
  Scenario Outline: Register and verify
    Given registration form
    When we add an user with <gender>, <firstName>,<lastName>, <password>, <day>, <month>, <year>, <street>, <city>, <state>, <zip>, <country>, <mobile>
    Then we should see user's account

     Examples:
          | gender | firstName | lastName | password  | day | month | year |  street   |   city    |  state   |  zip   |     country     |  mobile   |
          |  "F"   |  "Aaa"    |  "Bbbb"  |"password1"|  1  |  2    | 1900 | "Street1" | "Miasto1" | "Alabama" | 11111 | "United States" | 123456789 |
          |  "M"   |  "Ccc"    |  "Ddd"   |"password2"|  3  |  4    | 2000 | "Street2" | "Miasto2" | "Alabama" | 22222 | "United States" | 987654321 |
