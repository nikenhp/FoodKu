package com.example.user.foodku.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String lastname;
    private String firstname;
    private String deleted_at;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
