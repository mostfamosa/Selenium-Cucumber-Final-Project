package logic.entites.DTOs;

import lombok.Data;

import java.util.Map;

@Data
public class CartItem {
    private Map<String, Object> countUsesSale;
    private double FormatedSavePrice;
    private double FormatedTotalPrice;
    private double FormatedSavePriceClub;
    private double FormatedSavePriceWallet;
    private double FormatedTotalPriceClub;
    private double FormatedTotalPriceWallet;
    private double FormatedTotalPriceWithoutDiscount;
    private double finalPriceClub;
    private double finalPriceWallet;
    private Object PromotionId;
    private Object PromotionIdClub;
    private int department_id;
    private int group_id;
    private int site_id;
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int sub_group_id;
    private boolean has_coupon;
    private boolean is_delivery;
    private boolean isClub;
    private boolean isWallet;
}
