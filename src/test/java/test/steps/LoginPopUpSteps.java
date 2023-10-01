package test.steps;

import infra.ResponseWrapper;
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

    public LoginPopUpSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("I'm on Rami Levi Home Page")
    public void goToRamiLeviPage() {
        testContext.put(HOME_PAGE,new HomePage(testContext.get(KEY_DRIVER)));
    }

    @When("On the home page - I click on login")
    public void onTheHomePageIClickOnLogin() {
         HomePage homePage = testContext.get(HOME_PAGE);
         homePage.clickLogIn();
    }

    @When("On the popUp Login - I insert a valid user and click Login")
    public void onThePopUpLoginIInsertAnd() {
        HomePage homePage = testContext.get(HOME_PAGE);
        homePage.getLoginInPopUp().logInFullProcess(testContext.get(KEY_EMAIL), testContext.get(KEY_PASSWORD));
    }

    @When("Via Api - login to a valid user")
    public void viaApiLoginToAValidUser() {
        ResponseWrapper<UserResponse> login = RamiLeviApi.login(testContext.get(KEY_EMAIL), testContext.get(KEY_PASSWORD));
        UserResponse user = login.getData();
        testContext.put(USER_INFO,user);
    }

    @When("Update user in the local storage")
    public void updateUserInTheLocalStorage() {
        LocalStorageManager localStorageManager = new LocalStorageManager(testContext.get(KEY_DRIVER));
        localStorageManager.updateUserInTheLocalStorage(testContext.get(USER_INFO));
    }

    @When("Via Ui - refresh the page")
    public void refreshThePage() {
        WebDriver driver = testContext.get(KEY_DRIVER);
        driver.navigate().refresh();
    }

    @Then("The username will appear on the bar")
    public void theUsernameWillAppearOnTheBar() {
        HomePage homePage = testContext.get(HOME_PAGE);
        assertEquals(testContext.get(KEY_NAME), homePage.getProfileName());
    }


    // for all other scenarios this is a repeated steps for logging in.
    @Given("navigate to ramy levi home page to login")
    public void NavigateToRamyLeviHomePageToLogin() {
        //calling
        goToRamiLeviPage();
        viaApiLoginToAValidUser();
    }

    @Given("Update user in the local storage , refresh the page")
    public void updateUserInTheLocalStorageRefreshThePage() {
        //calling function
        updateUserInTheLocalStorage();
        refreshThePage();
    }

}
