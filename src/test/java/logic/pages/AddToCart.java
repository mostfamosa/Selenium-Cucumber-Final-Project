package logic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AddToCart extends BasePage{
    // Locators
    By FIRST_ELEMENT = By.id("min-height-product-0");
    By PLUS_SIGN_AFTER_HOVER = By.xpath("(//button[@class=\"focus-item btn-acc plus no-select\"])[1]");
    By INPUT_SEARCH = By.id("destination");
    By SEARCH_BUTTON = By.xpath("//button[@class=\"focus-item online-full-btn w-100 mt-3 mb-2 px-2 py-3 border-radius-20\"] ");
    By CLOSE_POP_UP = By.xpath("//div[@id=\"close-popup\"]");
    By DELETE_ALL_ITEMS = By.xpath("//div[@id=\"remove-cart\"]");
    By SURE_TO_DELETE = By.xpath("//button[@id=\"delete-cart-btn\"]");
    // Web Elements
    WebElement firstElement;
    WebElement plusSign;
    WebElement inputSearch;
    WebElement searchButton;
    WebElement closePopUp;
    WebElement deleteAllItems;
    WebElement sureToDelete;
    public AddToCart(WebDriver driver) {
        super(driver);
        initPage();
    }

    private void initPage() {

        inputSearch =waitToVisible(INPUT_SEARCH);

    }

    public void hoverOnFirstProduct(){
        firstElement = waitToVisible(FIRST_ELEMENT);
        Actions actions = new Actions(driver);
        actions.moveToElement(firstElement).perform();
    }
    public void addToCart(){
        hoverOnFirstProduct();
        plusSign = waitToVisible(PLUS_SIGN_AFTER_HOVER);
        plusSign.click();
        closePopUpAfterSearch();
    }
    public void search (String word){
        inputSearch.sendKeys(word);
        searchButton= waitToVisible(SEARCH_BUTTON);
        searchButton.click();

    }
    public void closePopUpAfterSearch(){
        if(isElementPresent(driver,CLOSE_POP_UP)) {
            closePopUp = waitToVisible(CLOSE_POP_UP);
            closePopUp.click();
        }
    }
    public void deleteAll(){
        deleteAllItems= waitToVisible(DELETE_ALL_ITEMS);
        deleteAllItems.click();
        sureToDelete = waitToVisible(SURE_TO_DELETE);
        sureToDelete.click();
    }


    // Function to check if an element is present on the page
    private static boolean isElementPresent(WebDriver driver, By by) {
        try {
            // Try to find the element
            WebElement element = driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            // Element not found
            return false;
        }
    }

}
