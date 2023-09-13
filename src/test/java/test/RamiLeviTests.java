package test;


import infra.WebDriverWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadFile;

import java.util.Properties;

public class RamiLeviTests {

    private WebDriverWrapper driverWrapper;
    private String testUrl = "https://www.rami-levy.co.il/he";

    private String userEmail;
    private String userPassword;

    @BeforeEach
    public void setUp() {
        driverWrapper = new WebDriverWrapper();
        driverWrapper.getDriver().get(testUrl);
        Properties prop = ReadFile.readPropertiesFile("userData.properties");
        userEmail = prop.getProperty("email");
        userPassword = prop.getProperty("password");
    }

    @AfterEach
    public void tearDown() {
        driverWrapper.closeDriver();
    }

    @Test
    public void check() {
        System.out.println(userEmail);
        System.out.println(userPassword);
    }


}
