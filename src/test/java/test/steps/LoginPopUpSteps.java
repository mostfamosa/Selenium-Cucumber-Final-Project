package test.steps;

import infra.ResponseWrapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.api.RamiLeviApi;
import logic.context.TestContext;
import logic.entites.DTOs.UserResponse;
import logic.entites.LocalStorageManager;
import logic.pages.HomePage;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPopUpSteps {
    private TestContext testContext;
    private HomePage homePage;
    private UserResponse user;

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

    @When("Via Api - login to a valid user")
    public void viaApiLoginToAValidUser() {
        ResponseWrapper<UserResponse> login = RamiLeviApi.login(testContext.get("email"), testContext.get("password"));
        user = login.getData();
    }

    @And("Update user in the local storage")
    public void updateUserInTheLocalStorage() {
        LocalStorageManager localStorageManager = new LocalStorageManager(testContext.get("driver"));
        localStorageManager.updateUserInTheLocalStorage(user);
    }

    @And("refresh the page")
    public void refreshThePage() {
        WebDriver driver = testContext.get("driver");
        driver.navigate().refresh();
    }

    @Then("The username will appear on the bar")
    public void theUsernameWillAppearOnTheBar() {
        assertEquals(testContext.get("name"), homePage.getProfileName());
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
