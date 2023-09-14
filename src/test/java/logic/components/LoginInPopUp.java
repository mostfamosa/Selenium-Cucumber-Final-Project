package logic.components;

import logic.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginInPopUp extends BasePage {

    // Locators
    private By EMAIL_INPUT = By.id("email");
    private By PASSWORD_INPUT = By.id("password");
    private By LOGIN_BUTTON = By.xpath("//button[@class = 'focus-item online-full-btn mt-4 px-5 height-50']");

    // Web Elements
    private WebElement emailInput;
    private WebElement passwordInput;
    private WebElement loginBtn;

    public LoginInPopUp(WebDriver driver) {
        super(driver);
        initComponent();
    }

    private void initComponent() {
        emailInput = waitToVisible(EMAIL_INPUT);
        passwordInput = waitToVisible(PASSWORD_INPUT);
        loginBtn = waitToVisible(LOGIN_BUTTON);
    }

    public void logInFullProcess(String email,String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }
}
