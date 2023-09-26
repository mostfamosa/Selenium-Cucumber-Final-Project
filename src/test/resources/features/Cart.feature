Feature: Adding/Deleting Items In Cart

  Scenario: adding a "cola" product to the cart to check existence
    Given navigate to ramy levi home page to login
    And Update user in the local storage , refresh the page
    When Via Api - add 2 cola products
    Then Via Ui - the cola product with it's price in the cart

#  Scenario: deleting a "cola" product from the cart
#    Given navigate to ramy levi home page to login
#    And Update user in the local storage , refresh the page
#    When Via Api - search for "cola" product to add it to cart
#    And via Ui - delete the "cola" product from cart
#    Then via UI - "cola" product is no longer in cart
#
#
#  Scenario: adding multiple items to cart to check the price/total
#    Given navigate to ramy levi home page to login
#    And Update user in the local storage , refresh the page
#    When Via Api - search for "cheese" product
#    And Via Api - adding the "cheese" product twice
#    Then Via UI - cart price is sum of the two picked products
#
#
#  Scenario: adding "pear" product once to see if the total calculate half kg
#    Given navigate to ramy levi home page to login
#    And Update user in the local storage , refresh the page
#    When Via Api - going to "vegetables and fruits" page
#    And Via Api - adding the "pear" product
#    Then Via UI - cart price is half of the price that displays on the "pear" product
