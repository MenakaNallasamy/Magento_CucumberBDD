Feature: Signin Data Driven

  @regression
  Scenario Outline: Signin Data Driven
    Given User Launch browser
    And opens URL "https://magento.softwaretestingboard.com/"
    When Click on Signin
    And User enters Email as "<email>" and Password as "<password>"
    And Click on Signin button
    Then User navigate to MyAccount Page

    Examples: 
      | email               | password     |
      | nickp3833@gmail.com | testdemo@146 |
      | gmena@gmail.com  | testdemo@146 |
