package logic.components;

import logic.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AddToCart extends BasePage {
    // Locators
    By FIRST_ELEMENT = By.id("min-height-product-0");
    By PLUS_SIGN_AFTER_HOVER = By.xpath("(//button[@class='focus-item btn-acc plus no-select'])[1]");
    By INPUT_SEARCH = By.id("destination");
    By SEARCH_BUTTON = By.xpath("//button[@class='focus-item online-full-btn w-100 mt-3 mb-2 px-2 py-3 border-radius-20'] ");
    By CLOSE_POP_UP = By.xpath("//div[@id='close-popup']");
    By DELETE_ALL_ITEMS = By.xpath("//div[@id='remove-cart']");
    By SURE_TO_DELETE = By.xpath("//button[@id='delete-cart-btn']");
    By CART_PRICE = By.xpath("//span[@class = 'position-relative currency-wrap overflow-ellipsis blue l-text']");
    By CART_EMPTY_SIGN = By.xpath("//*[name()='svg' and @xmlns= 'http://www.w3.org/2000/svg' and @width= '123.32']");
    By PLUS_SIGN_FOR_ITEM_IN_CART = By.xpath("//button[@class = 'focus-item btn-acc plus no-select']");
    private final String ITEM_IN_CART_LOCATOR = "//ul[@data-v-1980ce6d='']//li[%d]";


    // Web Elements
    WebElement firstElement;
    WebElement plusSign;
    WebElement inputSearch;
    WebElement searchButton;
    WebElement closePopUp;
    WebElement deleteAllItems;
    WebElement sureToDelete;
    WebElement cartPrice;
    WebElement cartEmptySign;
    WebElement itemInCart;
    WebElement plusSignOfItemInCart;

    public AddToCart(WebDriver driver) {
        super(driver);
        initPage();
    }

    private void initPage() {

        inputSearch = waitToVisible(INPUT_SEARCH);

    }

    private void hoverOnFirstProduct() {
        firstElement = waitToVisible(FIRST_ELEMENT);
        Actions actions = new Actions(driver);
        actions.moveToElement(firstElement).perform();
    }

    private void hoverOnProductInCartByIndex(int index) {
        By ITEM_IN_CART = By.xpath(String.format(ITEM_IN_CART_LOCATOR, index));
        itemInCart = waitToVisible(ITEM_IN_CART);
        Actions actions = new Actions(driver);
        actions.moveToElement(itemInCart).perform();
    }

    public void multiplyTheItemInCartWithIndex(int index) {
        hoverOnProductInCartByIndex(index);
        plusSignOfItemInCart = waitToVisible(PLUS_SIGN_FOR_ITEM_IN_CART);
        plusSignOfItemInCart.click();
    }

    public void addToCart() {
        hoverOnFirstProduct();
        plusSign = waitToVisible(PLUS_SIGN_AFTER_HOVER);
        plusSign.click();
        closePopUpAfterSearch();
    }

    public void search(String word) {
        inputSearch.sendKeys(word);
        searchButton = waitToVisible(SEARCH_BUTTON);
        searchButton.click();

    }

    public void closePopUpAfterSearch() {
        if (isElementPresent(driver, CLOSE_POP_UP)) {
            closePopUp = waitToVisible(CLOSE_POP_UP);
            closePopUp.click();
        }
    }

    public void deleteAll() {
        deleteAllItems = waitToVisible(DELETE_ALL_ITEMS);
        deleteAllItems.click();
        sureToDelete = waitToVisible(SURE_TO_DELETE);
        sureToDelete.click();
    }

    public String getCartPrice() {
        cartPrice = waitToVisible(CART_PRICE);
        return cartPrice.getText().replaceAll("[^0-9.]", "");
    }

    public boolean isTheCartEmpty() {
        cartEmptySign = waitToVisible(CART_EMPTY_SIGN);
        return cartEmptySign.isDisplayed();
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
