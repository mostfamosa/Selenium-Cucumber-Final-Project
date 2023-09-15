package test.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import infra.ResponseWrapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.api.RamiLeviApi;
import logic.context.TestContext;
import logic.entites.DTOs.AuthUserForLocalStorage;
import logic.entites.DTOs.UserResponseDTO;
import logic.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.ValidateJson;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPopUpSteps {
    private TestContext testContext;
    private HomePage homePage;
    private UserResponseDTO user;

    private final String keyForLocalStorage = "ramilevy";

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
        ResponseWrapper<UserResponseDTO> login = RamiLeviApi.login(testContext.get("email"), testContext.get("password"));
        user = login.getData();
    }

    @And("Update user in the local storage")
    public void updateUserInTheLocalStorage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) testContext.get("driver");
        String retrievedValue = (String) jsExecutor.executeScript("return localStorage.getItem(arguments[0]);", keyForLocalStorage);

        // Edit the retrieved value
        if (retrievedValue != null) {
            AuthUserForLocalStorage authuserUpdated = ValidateJson.getNodeFromJsonString(AuthUserForLocalStorage.class, retrievedValue, "authuser");
            authuserUpdated.setUser(user);

            // Execute JavaScript to update the item in local storage
            try {
                ObjectMapper ob = new ObjectMapper();
                jsExecutor.executeScript("" +
                        "var updatedLocalStorage = JSON.parse(localStorage.getItem(arguments[0]));" +
                        "updatedLocalStorage.authuser = JSON.parse(arguments[1]);" +
                        "console.log(updatedLocalStorage);" +
                        "localStorage.setItem(arguments[0], JSON.stringify(updatedLocalStorage));", keyForLocalStorage, ob.writeValueAsString(authuserUpdated.getUser()));

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
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

}
