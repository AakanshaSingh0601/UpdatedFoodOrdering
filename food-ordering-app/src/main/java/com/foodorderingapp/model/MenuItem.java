package com.foodorderingapp.model;

public class MenuItem {
    private int id;
    private String name;
    private double price;
    private ItemType type;
    private double calorieCount;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getMenuItemId() {
        return id;
    }
}
