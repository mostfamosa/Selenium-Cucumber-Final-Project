Feature: Adding/Deleting Items In Cart

  Scenario: adding a "cola" product to the cart to check existence
    Given navigate to ramy levi home page to login
    And Update user in the local storage , refresh the page
    When Via Api - add 2 cola products
    Then Via Ui - the cola product with it's price in the cart

  Scenario: deleting a "cola" product from the cart
    Given navigate to ramy levi home page to login
    And Update user in the local storage , refresh the page
    When Via Api - adding product with an id "386565" to cart
    And via Ui - delete the products from cart
    Then via UI - check that product is no longer in cart


  Scenario: adding multiple items to cart to check the price/total
    Given navigate to ramy levi home page to login
    And Update user in the local storage , refresh the page
    When Via Api - adding the "cheese" product first time
    And Via Ui - adding the "cheese" product second time
    Then Via UI - cart price is sum of the two picked products


  Scenario: adding "pear" product once to see if the total calculate half kg
    Given navigate to ramy levi home page to login
    And Update user in the local storage , refresh the page
    When Via Api - going to "vegetables and fruits" page
    And Via Api - adding the "pear" product
    Then Via UI - cart price is half of the price that displays on the "pear" product
