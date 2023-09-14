Feature: User Login Via UI
  Scenario: Login With A Valid User
    Given I'm on Rami Levi Home Page
    When On the home page - I click on login
    And On the popUp Login - I insert a valid user and click Login
    Then The username will appear on the bar