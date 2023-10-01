Feature: Adding/Deleting Items In Cart

  Background:
    #Given navigate to ramy levi home page to login
    #And Update user in the local storage , refresh the page
    When On the home page - I click on login
    And On the popUp Login - I insert a valid user and click Login

  @Regression
  Scenario Outline: adding a product to the cart then check existence
    When Via Api - add <quantity> products with id "<product_id>"
    Then Via Ui - validate that the products are in the cart
    Examples:
      | quantity | product_id |
      | 2        | 386565     |
      | 1        | 15         |
      | 3        | 376961     |
  #@Regression
  Scenario: deleting a product from the cart
    When Via Api - adding product with an id "386565" to cart
    And via Ui - delete the product from cart
    Then via UI - check that product is no longer in cart
  #@Regression
  Scenario: adding multiple items to cart to check the price/total
    When Via Api - adding product with an id "376961" to cart
    And Via Ui - multiply the product in the cart
    Then Via UI - check if the cart price is sum of the 2 picked products
  #@Regression
  Scenario: searching for product and add it to the cart then check existence
    When Via Ui - searching for "cola" product
    And Via Ui - adding product to cart
    Then Via UI - check if the cart updated