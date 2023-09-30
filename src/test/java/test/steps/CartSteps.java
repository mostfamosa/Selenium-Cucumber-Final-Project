package test.steps;

import infra.ResponseWrapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.api.RamiLeviApi;
import logic.context.TestContext;
import logic.entites.DTOs.AddItemResponse;
import logic.pages.HomePage;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;

import static org.hamcrest.Matchers.*;
import static org.junit.Assume.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartSteps {

    private TestContext testContext;
    private HomePage homePage;
    AddItemResponse itemResponse;

    public CartSteps(TestContext testContext) {
        this.testContext = testContext;
        homePage = new HomePage(testContext.get("driver"));
    }

    @Then("Via Ui - the products with it's price in the cart")
    public void viaUiTheColaProductWithItSPriceInTheCart() {
        assertEquals(itemResponse.getPrice(), Double.parseDouble(homePage.getAddToCart().getCartPrice()));
    }

    // adding two products of cola
    @When("Via Api - add {int} products with id {string}")
    public void viaApiAddProducts(int quantity, String itemId) {
        ResponseWrapper<AddItemResponse> response = RamiLeviApi.addItemToCart(itemId, quantity);
        itemResponse = response.getData();
        // We assume that the product added successfully to the cart
        assumeThat("The status is not 200!\nSomething went wrong!", 200, is(response.getStatus()));
    }

    //adding one product of
    @When("Via Api - adding product with an id {string} to cart")
    public void viaApiAddingProductWithAnIdToCart(String itemId) {
       viaApiAddProducts(1, itemId);
    }


    @And("via Ui - delete the product from cart")
    public void viaUiDeleteTheProductFromCart() {
        homePage.getAddToCart().deleteAll();
    }

    @Then("via UI - check that product is no longer in cart")
    public void viaUICheckThatProductIsNoLongerInCart() {
        assertTrue(homePage.getAddToCart().isTheCartEmpty());
    }



    @And("Via Ui - adding product for the second time to cart")
    public void ViaUiAddingProductForTheSecondTimeToCart() {
        homePage.getAddToCart().multiplyTheItemInCartWithIndex(1);
    }

    @Then("Via UI - check if the cart price is sum of the {int} picked products")
    public void viaUICheckIfTheCartPriceIsSumOfTheTwoPickedProducts(int quantity) {
        assertEquals(quantity * itemResponse.getPrice(), Double.parseDouble(homePage.getAddToCart().getCartPrice()));
    }


    @When("Via Ui - searching for {string} product")
    public void viaUiSearchingForProduct(String item) {
        homePage.getAddToCart().search(item);

    }

    @And("Via Ui - adding product to cart")
    public void viaUiAddingProductToCart() {
        homePage.getAddToCart().addToCart();
    }

    @Then("Via UI -check if cart price is bigger than zero")
    public void viaUICheckIfCartPriceIsBiggerThanZero() {
        Assert.assertTrue("Cart should have more than price 0",  Double.parseDouble(homePage.getAddToCart().getCartPrice())>0);
    }



}

