package test.steps;

import infra.ResponseWrapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.api.RamiLeviApi;
import logic.context.TestContext;
import logic.entites.DTOs.AddItemResponse;
import logic.pages.HomePage;

public class CartSteps {

    private TestContext testContext;
    private HomePage homePage;

    public CartSteps(TestContext testContext) {
        this.testContext = testContext;
        homePage = new HomePage(testContext.get("driver"));
    }

    @Then("Via Ui - the cola product with it's price in the cart")
    public void viaUiTheColaProductWithItSPriceInTheCart() {
    }

    @When("Via Api - add {int} cola products")
    public void viaApiAddColaProducts(int arg0) {
        ResponseWrapper<AddItemResponse> response = RamiLeviApi.addItemToCart("381050", arg0);
        AddItemResponse data = response.getData();
        System.out.println(data);
        System.out.println(response.getStatus());

    }
}
