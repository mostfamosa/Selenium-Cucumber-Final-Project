package logic.entites.responses;

import lombok.Data;

import java.util.List;

@Data
public class AuthUserForLocalStorage {
    private UserResponse user;
    private Object guestToken;
    private List<Object> shopLists;
}
