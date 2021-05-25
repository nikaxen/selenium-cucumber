@calc_predefined
  Feature: Salary Calculator
    Scenario Outline: Calculate annual amount of salary based on an hour wage
      Given Navigate to "https://www.calculator.net/salary-calculator.html"
      When Clear element with XPath "//input[@id='camount']"
      And Type "<PayRate>" into element with xpath "//input[@id='camount']"
      And Click on element with XPath "//input[@value='Calculate']"
      Then Element with XPath "//*[@id='content']/table[1]/tbody/tr/td[2]/table/tbody/tr[9]/td[3]" should contain text "<AnnualAmount>"
      Then Take screenshot
      Examples:
        | PayRate | AnnualAmount |
        | 35      | $65,800      |
        | 45      | $84,600      |
        | 50      | $94,000      |
        | 55      | $103,400     |