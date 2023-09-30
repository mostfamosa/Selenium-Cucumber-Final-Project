package logic.entites.responses;

import lombok.Data;

import java.util.List;

@Data
public class AddItemResponse {
    private List<Object> sales;
    private List<CartItem> items;
    private Object log_id;
    private double price;
    private double priceClub;
    private double discountClub;
    private double priceWallet;
    private double discountWallet;
    private double discount;
    private int quantity;
    private Meta meta;
    private int status;

}

