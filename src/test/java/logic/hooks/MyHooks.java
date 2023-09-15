package logic.hooks;

import infra.WebDriverWrapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import logic.context.TestContext;
import logic.pages.HomePage;
import utils.ReadFile;

import java.util.Properties;

import static infra.WebDriverWrapper.takeScreenshot;

public class MyHooks {
    private static TestContext testContext;
    private WebDriverWrapper driverWrapper;
    private HomePage homePage;
    private String testUrl = "https://www.rami-levy.co.il/he";
    private String userEmail;
    private String userPassword;
    private String userName;


    public MyHooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void beforeScenario() {
        driverWrapper = new WebDriverWrapper();
        driverWrapper.getDriver().get(testUrl);
        // Maximize the browser window
        driverWrapper.getDriver().manage().window().maximize();

        // Read property file and get user data
        Properties prop = ReadFile.readPropertiesFile("userData.properties");
        userEmail = prop.getProperty("email");
        userPassword = prop.getProperty("password");
        userName = prop.getProperty("name");

        homePage = new HomePage(driverWrapper.getDriver());

        testContext.put("driver", driverWrapper.getDriver());
        testContext.put("email", userEmail);
        testContext.put("password", userPassword);
        testContext.put("name", userName);
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(scenario.getName(), testContext);
        }
        testContext = null;

        // Close the WebDriver (close browser)
        driverWrapper.closeDriver();
    }
}
