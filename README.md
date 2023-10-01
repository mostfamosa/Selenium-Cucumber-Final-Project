# Selenium-Cucumber-Final-Project
## Intro
We have talked a lot about testing, selenium, cucumber, and more.
Now it’s time to put it all together!
In this project we will write a test plan, develop automation infrastructure, and implement all kinds of test.
We will use best practices of test automation and of the tools we learned.
We will be testing the following site:
rami-levy.co.il
You can choose your own site to perform test on assuming it answers the following criteria:
* You need to be logged in
* You can make api calls and see their effect in the website
* You have enough content to test
-> We went with rami-levi.co.il

## Guidelines
### API & UI
* Your tests should include api calls for data preparation.
* Actions under tests should be performed in the UI
* Validations should be done in the UI
* You can add extra validations via API

### AAA
* You should adhere to the Arrange, Act, Assert structure

### Test isolation (independence)
* All tests should be independent and not affect or be affected by other tests

### Parallelism
* Your tests should run in parallel

### Assertions
* Use the most accurate assertions

### Reporting
* Test results should be presented using allure report
  
### infrastructure
* Code should be structured in layers (infra, logic, tests)
* Selenium should not be revealed to the tests layer
* Use POM

### BDD
* Make sure to use the correct language &wording in your cucumber steps

### GIT
* Use pull requests and have code reviews
* Use informative commit messages

### Stability
* Make sure your tests pass consistently, no flaky tests!
* Tests may fail if there are actual bugs in the system

### Test Case
* https://docs.google.com/spreadsheets/d/1NB0QqqlriIewIOU1cFRNnEFPFgwoFd6d6M6PmO2Zp0g/edit?usp=sharing

### Test Plan
* https://docs.google.com/document/d/1uyNNlkm7h34l5G2eTf7eC0q8U1QkLkdnz-LI_JO7nGI/edit?usp=sharing
