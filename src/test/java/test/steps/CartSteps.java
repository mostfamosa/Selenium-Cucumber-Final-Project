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

    @Then("Via Ui - the cola product with it's price in the cart")
    public void viaUiTheColaProductWithItSPriceInTheCart() {
        assertEquals(itemResponse.getPrice(), Double.parseDouble(homePage.getAddToCart().getCartPrice()));
    }

    @When("Via Api - add {int} cola product with id {string}")
    public void viaApiAddColaProducts(int quantity, String itemId) {
        ResponseWrapper<AddItemResponse> response = RamiLeviApi.addItemToCart(itemId, quantity);
        itemResponse = response.getData();
        // We assume that the product added successfully to the cart
        assumeThat("The status is not 200!\nSomething went wrong!", 200, is(response.getStatus()));
    }

    @When("Via Api - adding product with an id {string} to cart")
    public void viaApiAddingProductWithAnIdToCart(String itemId) {
        ResponseWrapper<AddItemResponse> response = RamiLeviApi.addItemToCart(itemId, 1);
        itemResponse = response.getData();
        // We assume that the product added successfully to the cart
        assumeThat("The status is not 200!\nSomething went wrong!", 200, is(response.getStatus()));
    }

    @And("via Ui - delete the products from cart")
    public void viaUiDeleteTheProductFromCart() {
        homePage.getAddToCart().deleteAll();
    }

    @Then("via UI - check that product is no longer in cart")
    public void viaUICheckThatProductIsNoLongerInCart() {
        assertTrue(homePage.getAddToCart().isTheCartEmpty());
    }


    @When("Via Api - adding product with an id {string} first time to cart")
    public void viaApiAddingProductWithAnIdFirstTimeToCart(String itemId) {
        ResponseWrapper<AddItemResponse> response = RamiLeviApi.addItemToCart(itemId, 1);
        itemResponse = response.getData();
        // We assume that the product added successfully to the cart
        assumeThat("The status is not 200!\nSomething went wrong!", 200, is(response.getStatus()));
    }

    @And("Via Ui - adding product for the second time to cart")
    public void ViaUiAddingProductForTheSecondTimeToCart() {
        homePage.getAddToCart().addToCart();
    }

//    @Then("Via UI - check if the cart price is sum of the two picked products")
//    public void viaUICheckIfTheCartPriceIsSumOfTheTwoPickedProducts() {
//
//        double Sum = viaUiAddingProductWithAnIdSecondTimeToCart()+ viaApiAddingProductWithAnIdFirstTimeToCart();
//        assertEquals(Sum, homePage.getAddToCart().getCartPrice());
//    }



    @Then("Via UI - cart price is half of the price that displays on the product")
    public void viaUICartPriceIsHalfOfThePriceThatDisplaysOnTheProduct() {
        assertEquals((itemResponse.getPrice())/2, Double.parseDouble(homePage.getAddToCart().getCartPrice()));
    }
}

