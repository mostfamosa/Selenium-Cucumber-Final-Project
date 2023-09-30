package logic.entites;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.entites.responses.AuthUserForLocalStorage;
import logic.entites.responses.UserResponse;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import utils.ValidateJson;

public class LocalStorageManager {
    private LocalStorage localStorage;
    private WebDriver driver;
    private final String keyForLocalStorage = "ramilevy";
    private final String jsonObjectAuthUserToChange = "authuser";

    public LocalStorageManager(WebDriver driver) {
        this.driver = driver;
        this.localStorage = ((WebStorage) driver).getLocalStorage();
    }

    public void updateUserInTheLocalStorage(UserResponse user) {
        String retrievedValue = localStorage.getItem(keyForLocalStorage);

        // Edit the retrieved value
        if (retrievedValue != null) {
            AuthUserForLocalStorage authuserUpdated = ValidateJson.getNodeFromJsonString(AuthUserForLocalStorage.class, retrievedValue, jsonObjectAuthUserToChange);
            authuserUpdated.setUser(user);

            // Execute JavaScript to update the item in local storage
            try {
                ObjectMapper ob = new ObjectMapper();
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("" +
                        "var updatedLocalStorage = JSON.parse(localStorage.getItem(arguments[0]));" +
                        "updatedLocalStorage.authuser = JSON.parse(arguments[1]);" +
                        "localStorage.setItem(arguments[0], JSON.stringify(updatedLocalStorage));", keyForLocalStorage, ob.writeValueAsString(authuserUpdated.getUser()));

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
