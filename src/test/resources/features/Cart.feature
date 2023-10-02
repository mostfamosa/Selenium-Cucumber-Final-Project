Feature: Adding/Deleting Items In Cart

  Background:
    #Given navigate to ramy levi home page to login
    #And Update user in the local storage , refresh the page
    Given I'm on Rami Levi Home Page
    When On the home page - I click on login
    And On the popUp Login - I insert a valid user and click Login

  #@Regression
  Scenario Outline: Adding products via api then check the final price of the cart
    When Via Api - add <quantity> products with id "<product_id>"
    And Via Ui - refresh the page
    Then Via Ui - validate that the products total price equals to the the cart price
    Examples:
      | quantity | product_id |
      | 2        | 386565     |
      | 1        | 15         |
      | 3        | 376961     |

  #@Regression
  Scenario: Check if the products deleted from the cart
    When Via Api - adding product with an id "386565" to cart
    And Via Ui - refresh the page
    And via Ui - delete the products from cart
    Then via UI - check if the cart empty sign is displayed

  #@Regression
  Scenario: adding multiple items to cart to check the price/total
    When Via Api - adding product with an id "376961" to cart
    And Via Ui - multiply the product in the cart
    Then Via UI - check if the cart price is sum of the 2 picked products

  @Regression
  Scenario: searching for product and add it to the cart then check by title
    When Via Ui - searching for "מי סודה 200מל שישייה" products
    And Via Ui - adding the top search result product to the cart
    Then Via UI - check if product title match the one in cart