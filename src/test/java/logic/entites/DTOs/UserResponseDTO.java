package logic.entites.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private User user;
    private DeliveryTimesCustomer deliveryTimesCustomer;
    private List<String> orderTokens;
    private Order order;
    private Cart cart;
    private List<Object> shopLists;

    @Data
    public static class User {
        private int id;
        private String first_name;
        private String last_name;
        private String email;
        private String token;
        private String user_id;
        private Object meta;
        private Integer store_id;
        private Object locale;
        private String identity_card;
        private Integer accounting_card_id;
        private int business;
        private Object birth_date;
        private Object phone;
        private Object additional_phone;
        private Integer sex_id;
        private Object edit_order;
        private int send_email;
        private int send_sms;
        private String subscribe_at;
        private String subscribe_ip;
        private String club_accept_at;
        private String club_accept_ip;
        private int club_accept;
        private int send_club_database;
        private Object disabled_at;
        private Object activated_at;
        private Object deleted_at;
        private Object area_id;
        private Object deactivated_at;
        private Object customer_id_club;
        private String name;
        private List<Object> orders;
        private List<Object> cards;
        private List<Object> addresses;
        private String popularProducts;
        private List<Object> features;
        private List<Object> coupons;

    }

    public static class DeliveryTimesCustomer {
        // Add fields for DeliveryTimesCustomer if available
    }

    public static class Order {
        // Add fields for Order if available
    }

    public static class Cart {
        // Add fields for Cart if available
    }
}