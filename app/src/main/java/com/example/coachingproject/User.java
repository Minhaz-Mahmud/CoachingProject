package com.example.coachingproject;

public class User {
    private static User instance = null;
    public String name;
    public String address;
    public String phone;
    public String email;
    public String roll;

    private User() {

    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public User(String name, String address, String phone, String email, String roll) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.roll = roll;
    }
}
