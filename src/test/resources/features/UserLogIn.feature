Feature: User Login
  #@Regression
  Scenario: Login With A Valid User Via UI
    Given I'm on Rami Levi Home Page
    When On the home page - I click on login
    And On the popUp Login - I insert a valid user and click Login
    Then The username will appear on the bar
  #@Regression
  Scenario: Login With A Valid User Via API
    Given I'm on Rami Levi Home Page
    When Via Api - login to a valid user
    And Update user in the local storage
    And Via Ui - refresh the page
    Then The username will appear on the bar