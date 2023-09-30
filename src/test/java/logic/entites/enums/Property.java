package logic.entites.enums;

public enum Property {
    PROPERTY_EMAIL("email"),
    PROPERTY_PASSWORD("password"),
    PROPERTY_NAME("name"),
    ;

    private String key;
    Property(String prop) {
        key = prop;
    }

    public String getKey() {
        return key;
    }
}
