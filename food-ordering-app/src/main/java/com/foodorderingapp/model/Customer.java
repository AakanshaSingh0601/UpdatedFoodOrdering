package com.foodorderingapp.model;

public class Customer {
    private String name;
    private String address;
    private String email;

    private String userType;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private String password;
    // Additional attributes and methods as needed

    // Constructor, getters, and setters

    public Customer(String name, String address, String email, String password,String userType) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.userType= userType;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}