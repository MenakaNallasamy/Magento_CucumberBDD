Feature: Signin Data Driven with Excel

@sanity @regression
  Scenario Outline: Signin Data Driven with Excel
    Given User Launch browser
    And opens URL "https://magento.softwaretestingboard.com/"
    When click on Signin 
    Then check User navigates to MyAccount Page by passing Email and Password with excel row "<row_index>"

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
      