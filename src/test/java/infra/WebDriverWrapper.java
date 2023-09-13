package infra;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverWrapper {
    protected WebDriver driver;

    public WebDriverWrapper() {

    }

    public WebDriver getDriver() {
        if (driver == null) {
            // Set up the chrome driver
            WebDriverManager.chromedriver().setup();
            // Initialize the WebDriver
            driver = new ChromeDriver();
        }
        return driver;
    }


    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void closeDriver() {
        if (driver != null) {
            driver.close();
        }
    }
}
