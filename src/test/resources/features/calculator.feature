@CalculatorFeature
  Feature: Salary calculator test

    Scenario: Verify search of a calculator
      Given I navigate to the "home" page
      When I fill out Search field with "Salary Calculator"
      Then I submit the search form
      Then Top search result contains text "Salary Calculator"
      Then Click on a top search result
      Then Page title is "Salary Calculator"

    Scenario Outline: Verify calculations of a Salary Calculator
      Given I navigate to the "Salary Calculator" page
      Then I fill out "Salary Amount" field with "<salary_amount>"
      Then I select "<per_unit>" in the dropdown list
      Then I fill out "Hours per Week" field with "<hours_per_week>"
      Then I fill out "Days per Week" field with "<days_per_week>"
      Then I fill out "Holidays per Year" field with "<holidays_per_year>"
      Then I fill out "Vacation Days per Year" field with "<vacation_days_per_year>"
      When I click on the Calculate button
      Then I should see "<unadjusted_amount>" in "Unadjusted" field
      And I should see "<adjusted_amount>" in "Adjusted" field
      Examples:
        | salary_amount | per_unit | hours_per_week | days_per_week | holidays_per_year | vacation_days_per_year | unadjusted_amount | adjusted_amount |
        | 25            | Hour     | 40             | 5             | 10                | 15                     | $52,000           | $47,000         |
        | 35            | Hour     | 40             | 5             | 10                | 15                     | $72,800           | $65,800         |
        | 45            | Hour     | 40             | 5             | 10                | 15                     | $93,600           | $84,600         |
        | 55            | Hour     | 40             | 5             | 10                | 15                     | $114,400          | $103,400        |
        | 400           | Day      | 40             | 5             | 10                | 15                     | $104,000          | $94,000         |
        | 2000          | Week     | 40             | 5             | 10                | 15                     | $115,064          | $104,000        |
        | 9000          | Month    | 40             | 5             | 10                | 15                     | $119,489          | $108,000        |