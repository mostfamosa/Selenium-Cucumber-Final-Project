package logic.pages;

import logic.components.LoginInPopUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    LoginInPopUp loginInPopUp;
    // Locators
    private By LOGIN_BUTTON = By.xpath("//div[@class = 'd-lg-block blue align-self-center mx-1 m-text d-none']");
    private By PROFILE_NAME = By.xpath("//div[@class = 'd-flex align-items-center m-text']//span");
    // Web Elements
    private WebElement loginBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        initPage();
    }

    private void initPage() {
        loginBtn = waitToVisible(LOGIN_BUTTON);
    }

    public void clickLogIn(){
        loginBtn.click();
        loginInPopUp = new LoginInPopUp(driver);
    }

    public LoginInPopUp getLoginInPopUp() {
        return loginInPopUp;
    }

    //after login check
    public String getProfileName(){
        return waitToVisible(PROFILE_NAME).getText();
    }
}
