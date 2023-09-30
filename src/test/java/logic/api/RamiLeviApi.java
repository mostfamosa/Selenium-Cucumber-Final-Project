package logic.api;


import infra.HttpRequest;
import infra.ResponseWrapper;
import logic.entites.DTOs.AddItemDTO;
import logic.entites.responses.AddItemResponse;
import logic.entites.DTOs.UserDTO;
import logic.entites.responses.UserResponse;

import java.util.HashMap;

import static logic.entites.enums.HttpMethods.POST;

public class RamiLeviApi {

    private static UserResponse user;
    private static final String BASE_URL_LOGIN = "https://api-prod.rami-levy.co.il/api/v2/site/auth/login";
    private static final String BASE_URL = "https://www.rami-levy.co.il/api/";
    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3d3dy1hcGkucmFtaS1sZXZ5LmNvLmlsIiwiYXVkIjoiaHR0cHM6Ly93d3ctYXBpLnJhbWktbGV2eS5jby5pbCIsImlhdCI6MTY5NjAwMDE2NC4yOTIzNjksIm5iZiI6MTY5NjAwMDIyNC4yOTIzNjksImV4cCI6MTcwMTE4Nzc2NC4yOTIzNjksImlkIjo4ODkzMzQsImVtYWlsIjoibmFkaWEubW9hbGVtMTIzQGhvdG1haWwuY29tIiwiY2lkIjoiOTkwMDE1NDc1NTgifQ.ZZ5tV5DtH7fGFREMDr0U7Ao670O385TWXPXoJuXENMA";
    public static ResponseWrapper<UserResponse> login(String email, String password) {
        ResponseWrapper<UserResponse> request = HttpRequest.request(POST, BASE_URL_LOGIN, new UserDTO(email, password), UserResponse.class);
        user = request.getData();
        return request;
    }

    public static ResponseWrapper<AddItemResponse> addItemToCart(String itemId, double quantity) {
        HashMap<String,String> header = new HashMap<>();
        header.put("Ecomtoken", token);//user.getUser().getToken()
        return HttpRequest.request(POST, BASE_URL+"v2/cart",new AddItemDTO(itemId,quantity),header,AddItemResponse.class );
    }

    public static ResponseWrapper<AddItemResponse> deleteItemFromCart(String itemId) {
        return addItemToCart(itemId,0);
    }
}
