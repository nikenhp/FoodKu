package com.example.user.foodku.model;

import java.util.ArrayList;

public class MenuModel {
    private String code;
    private Boolean status;
    private String description;
    private String message;
    private ArrayList <Menu> menu;

    public String getCode() {
        return code;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Menu> getMenu() {
        return menu;
    }
}
