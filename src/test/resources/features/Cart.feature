Feature: Adding/Deleting Items In Cart

  Background:
    #Given navigate to ramy levi home page to login
    #And Update user in the local storage , refresh the page
    When On the home page - I click on login
    And On the popUp Login - I insert a valid user and click Login

  Scenario: adding a product to the cart then check existence
    When Via Api - add 2 products with id "386565"
    Then Via Ui - the products with it's price in the cart

  Scenario: deleting a product from the cart
    When Via Api - adding product with an id "386565" to cart
    And via Ui - delete the product from cart
    Then via UI - check that product is no longer in cart


  Scenario: adding multiple items to cart to check the price/total
    When Via Api - adding product with an id "376961" to cart
    And Via Ui - adding product for the second time to cart
    Then Via UI - check if the cart price is sum of the 2 picked products


  Scenario: searching for product to adding it and check if the cart price updated
    When Via Ui - searching for "cola" product
    And Via Ui - adding product to cart
    Then Via UI -check if cart price is bigger than zero
