package test.steps;

import infra.ResponseWrapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.api.RamiLeviApi;
import logic.context.TestContext;
import logic.entites.responses.AddItemResponse;
import logic.pages.HomePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.*;
import static org.junit.Assume.*;
import static org.junit.jupiter.api.Assertions.*;
import static logic.entites.enums.TestContextKey.*;

public class CartSteps {

    private TestContext testContext;

    public CartSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Then("Via Ui - validate that the products total price equals to the the cart price")
    public void viaUiTheColaProductWithItSPriceInTheCart() {
        HomePage homePage = testContext.get(HOME_PAGE);
        AddItemResponse itemResponse = testContext.get(ITEM_RESPONSE);
        assertEquals(itemResponse.getPrice(), Double.parseDouble(homePage.getAddToCart().getCartPrice()));
    }

    // adding two products of cola
    @When("Via Api - add {int} products with id {string}")
    public void viaApiAddProducts(int quantity, String itemId) {
        ResponseWrapper<AddItemResponse> response = RamiLeviApi.addItemToCart(itemId, quantity);
        AddItemResponse itemResponse = response.getData();
        // We assume that the product added successfully to the cart
        assumeThat("The status is not 200!\nSomething went wrong!", 200, is(response.getStatus()));
        testContext.put(ITEM_ID, itemId);
        testContext.put(ITEM_RESPONSE, itemResponse);
        WebDriver driver = testContext.get(KEY_DRIVER);
        driver.navigate().refresh();
    }

    //adding one product of
    @When("Via Api - adding product with an id {string} to cart")
    public void viaApiAddingProductWithAnIdToCart(String itemId) {
        viaApiAddProducts(1, itemId);
    }

    @When("via Ui - delete the products from cart")
    public void viaUiDeleteTheProductFromCart() {
        HomePage homePage = testContext.get(HOME_PAGE);
        homePage.getAddToCart().deleteAll();
    }

    @Then("via UI - check if the cart empty sign is displayed")
    public void viaUICheckThatProductIsNoLongerInCart() {
        HomePage homePage = testContext.get(HOME_PAGE);
        assertTrue(homePage.getAddToCart().isTheCartEmpty());
    }

    @When("Via Ui - multiply the product in the cart")
    public void ViaUiAddingProductForTheSecondTimeToCart() {
        HomePage homePage = testContext.get(HOME_PAGE);
        homePage.getAddToCart().multiplyTheItemInCartWithIndex(1);
    }

    @Then("Via UI - check if the cart price is sum of the {int} picked products")
    public void viaUICheckIfTheCartPriceIsSumOfTheTwoPickedProducts(int quantity) {
        HomePage homePage = testContext.get(HOME_PAGE);
        AddItemResponse itemResponse = testContext.get(ITEM_RESPONSE);
        assertEquals(quantity * itemResponse.getPrice(), Double.parseDouble(homePage.getAddToCart().getCartPrice()));
    }

    @When("Via Ui - searching for {string} products")
    public void viaUiSearchingForProduct(String item) {
        HomePage homePage = testContext.get(HOME_PAGE);
        homePage.getAddToCart().search(item);
    }

    @When("Via Ui - adding the top search result product to the cart")
    public void viaUiAddingProductToCart() {
        HomePage homePage = testContext.get(HOME_PAGE);
        homePage.getAddToCart().addToCart();
    }

    @Then("Via UI - check if product title match the one in cart")
    public void viaUICheckIfCartPriceIsBiggerThanZero() {
        HomePage homePage = testContext.get(HOME_PAGE);
        String title = homePage.getAddToCart().getFirstElementTitle();
        Assert.assertEquals("Cart should have more than price 0", title,false);
    }

}

