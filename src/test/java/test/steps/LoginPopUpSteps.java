package test.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.context.TestContext;
import logic.pages.HomePage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPopUpSteps {
    private TestContext testContext;
    private HomePage homePage;

    public LoginPopUpSteps(TestContext testContext) {
        this.testContext = testContext;
        homePage = new HomePage(testContext.get("driver"));
    }

    @Given("I'm on Rami Levi Home Page")
    public void goToRamiLeviPage() {
        //in the @before hook already get the page
    }

    @When("On the home page - I click on login")
    public void onTheHomePageIClickOnLogin() {
        homePage.clickLogIn();
    }

    @And("On the popUp Login - I insert a valid user and click Login")
    public void onThePopUpLoginIInsertAnd() {
        homePage.getLoginInPopUp().logInFullProcess(testContext.get("email"), testContext.get("password"));
    }

    @Then("The username will appear on the bar")
    public void theUsernameWillAppearOnTheBar() {
        assertEquals(testContext.get("name"), homePage.getProfileName());
    }
}
