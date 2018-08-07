package com.example.user.foodku.model;

public class LoginModel {
    private Boolean status;
    private String message;
    private String code;
    private String description;
    private String name;
    private String email;
    private Data data;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Data getData() {
        return data;
    }
}
