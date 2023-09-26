package logic.api;


import infra.HttpRequest;
import infra.ResponseWrapper;
import logic.context.TestContext;
import logic.entites.DTOs.AddItemDTO;
import logic.entites.DTOs.AddItemResponse;
import logic.entites.DTOs.UserDTO;
import logic.entites.DTOs.UserResponse;

import java.util.HashMap;
import java.util.Map;

import static logic.entites.enums.HttpMethods.POST;

public class RamiLeviApi {

    private static UserResponse user;
    private static final String BASE_URL_LOGIN = "https://api-prod.rami-levy.co.il/api/v2/site/auth/login";
    private static final String BASE_URL = "https://www.rami-levy.co.il/api/";

    public static ResponseWrapper<UserResponse> login(String email, String password) {
        ResponseWrapper<UserResponse> request = HttpRequest.request(POST, BASE_URL_LOGIN, new UserDTO(email, password), UserResponse.class);
        user = request.getData();
        return request;
    }

    public static ResponseWrapper<AddItemResponse> addItemToCart(String itemId, int quantity) {
        HashMap<String,String> header = new HashMap<>();
        header.put("Ecomtoken", user.getUser().getToken());
        return HttpRequest.request(POST, BASE_URL+"v2/cart",new AddItemDTO(itemId,quantity),header,AddItemResponse.class );
    }
}
