package logic.entites.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class AddItemDTO {
    private int isClub;
    private Map<String, String> items;
    private String meta;
    private int store;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date supplyAt;

    public AddItemDTO() {
    }
}
