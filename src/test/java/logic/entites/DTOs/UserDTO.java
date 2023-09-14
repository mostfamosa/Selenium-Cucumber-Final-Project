package logic.entites.DTOs;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;


    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO() {
    }
}
