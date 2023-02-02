Feature: Signin with Valid Credientials

# ctrl+shift+F- alignment/pretty format(intendation is very important)

@sanity
  Scenario: Successful Signin with Valid Credientials
    Given User Launch browser
    And opens URL "https://magento.softwaretestingboard.com/"
    When Click on Signin
    And User enters Email as "nickp3833@gmail.com" and Password as "testdemo@146"
    And Click on Signin button
    Then User navigate to MyAccount Page
