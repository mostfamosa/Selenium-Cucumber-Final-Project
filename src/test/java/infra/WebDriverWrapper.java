package infra;

import io.github.bonigarcia.wdm.WebDriverManager;
import logic.context.TestContext;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static logic.entites.enums.TestContextKey.*;

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

    public static byte[] takeScreenshot(String scenName, TestContext testContext) {
        // Capture a screenshot
        byte[] screenshot = ((TakesScreenshot) testContext.get(KEY_DRIVER)).getScreenshotAs(OutputType.BYTES);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        try {
            File screenshot2 = ((TakesScreenshot) testContext.get(KEY_DRIVER)).getScreenshotAs(OutputType.FILE);
            // Specify the file path where you want to save the screenshot
            FileUtils.copyFile(screenshot2, new File(String.format("screenshot_%s_%s.png", scenName, LocalDateTime.now().format(format))));
            return screenshot;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
