package test.steps;

import infra.ResponseWrapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.api.RamiLeviApi;
import logic.context.TestContext;
import logic.entites.responses.UserResponse;
import logic.entites.LocalStorageManager;
import logic.pages.HomePage;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;
import static logic.entites.enums.TestContextKey.*;

public class LoginPopUpSteps {
    private TestContext testContext;
    private HomePage homePage;
    private UserResponse user;

    public LoginPopUpSteps(TestContext testContext) {
        this.testContext = testContext;
        homePage = new HomePage(testContext.get(KEY_DRIVER));
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
        homePage.getLoginInPopUp().logInFullProcess(testContext.get(KEY_EMAIL), testContext.get(KEY_PASSWORD));
    }

    @When("Via Api - login to a valid user")
    public void viaApiLoginToAValidUser() {
        ResponseWrapper<UserResponse> login = RamiLeviApi.login(testContext.get(KEY_EMAIL), testContext.get(KEY_PASSWORD));
        user = login.getData();
    }

    @And("Update user in the local storage")
    public void updateUserInTheLocalStorage() {
        LocalStorageManager localStorageManager = new LocalStorageManager(testContext.get(KEY_DRIVER));
        localStorageManager.updateUserInTheLocalStorage(user);
    }

    @And("refresh the page")
    public void refreshThePage() {
        WebDriver driver = testContext.get(KEY_DRIVER);
        driver.navigate().refresh();
    }

    @Then("The username will appear on the bar")
    public void theUsernameWillAppearOnTheBar() {
        assertEquals(testContext.get(KEY_NAME), homePage.getProfileName());
    }


    // for all other scenarios this is a repeated steps for logging in.
    @Given("navigate to ramy levi home page to login")
    public void NavigateToRamyLeviHomePageToLogin() {
        //calling
        goToRamiLeviPage();
        viaApiLoginToAValidUser();
    }

    @And("Update user in the local storage , refresh the page")
    public void updateUserInTheLocalStorageRefreshThePage() {
        //calling function
        updateUserInTheLocalStorage();
        refreshThePage();
    }

}
