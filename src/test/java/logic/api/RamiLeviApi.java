package logic.api;


import infra.HttpRequest;
import infra.ResponseWrapper;
import logic.entites.DTOs.UserDTO;
import logic.entites.DTOs.UserResponseDTO;

import static logic.entites.enums.HttpMethods.POST;

public class RamiLeviApi {

    private static final String BASE_URL_LOGIN = "https://api-prod.rami-levy.co.il/api/v2/site/auth/login";
    private static final String BASE_URL = "https://www.rami-levy.co.il/api/";

    public static ResponseWrapper<UserResponseDTO> login(String email,String password){
        return HttpRequest.request(POST,BASE_URL_LOGIN,new UserDTO(email,password),UserResponseDTO.class);
    }
}
