package logic.entites.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class AuthUserForLocalStorage {
    private UserResponseDTO user;
    private Object guestToken;
    private List<Object> shopLists;
}