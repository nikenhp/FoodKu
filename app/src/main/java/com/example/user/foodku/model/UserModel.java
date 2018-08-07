package com.example.user.foodku.model;

import java.util.ArrayList;

public class UserModel {
    private boolean status;
    private String message;
    private ArrayList<User> data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<User> getData() {
        return data;
    }
}
