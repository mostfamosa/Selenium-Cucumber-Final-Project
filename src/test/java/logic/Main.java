package logic;

import infra.ResponseWrapper;
import infra.WebDriverWrapper;
import logic.api.RamiLeviApi;
import logic.entites.DTOs.UserResponse;
import logic.entites.LocalStorageManager;
import logic.pages.AddToCart;


public class Main {
    public static void main(String[] args) {
        WebDriverWrapper driverWrapper = new WebDriverWrapper();
        driverWrapper.getDriver().get("https://www.rami-levy.co.il/he");
        // Maximize the browser window
        driverWrapper.getDriver().manage().window().maximize();

        ResponseWrapper<UserResponse> login = RamiLeviApi.login("nadia.moalem123@hotmail.com", "Nm03061998Nm");
        UserResponse user = login.getData();

        LocalStorageManager localStorageManager = new LocalStorageManager(driverWrapper.getDriver());
        localStorageManager.updateUserInTheLocalStorage(user);



        System.out.println(RamiLeviApi.addItemToCart("122431",2));

        //localStorageManager.addPackOfColaToCart(5);

        driverWrapper.refreshPage();

        AddToCart addToCart = new AddToCart(driverWrapper.getDriver());
        System.out.println(addToCart.getCartPrice());
    }
}
