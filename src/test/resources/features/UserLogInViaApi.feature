Feature: User Login Via API
  Scenario: Login With A Valid User
    Given I'm on Rami Levi Home Page
    When Via Api - login to a valid user
    And refresh the page
    Then The username will appear on the bar