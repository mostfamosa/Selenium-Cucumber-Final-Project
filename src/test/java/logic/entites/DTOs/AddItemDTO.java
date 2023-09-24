package logic.entites.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.*;

@Data
public class AddItemDTO {
    private final int isClub = 0;
    private Map<String, String> items;
    private final String meta = null;
    private final int store = 331;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String supplyAt;

    public AddItemDTO() {
    }

    public AddItemDTO(String itemId, int quantity) {

        //format for the API body request
        SimpleDateFormat isoFormat  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date();

        //init for the map
        this.items = new HashMap<>();

        //add item to the map
        this.items.put(itemId, Integer.toString(quantity));

        //set current Date And Time
        this.supplyAt = isoFormat.format(date);

    }
}
