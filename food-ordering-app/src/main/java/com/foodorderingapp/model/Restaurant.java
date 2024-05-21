package com.foodorderingapp.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private long restaurantID;
    private String name;
    private String Location;
    private CuisineType cuisineType;
    private List<MenuItem> menu;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private boolean isActive;
    private double rating;
    private List<Order> orders;

//    public Restaurant(long restaurantID, String name, String location, CuisineType cuisineType,
//                      List<MenuItem> menu, LocalTime openingTime, LocalTime closingTime, boolean isActive, double rating) {
//        this.restaurantID = restaurantID;
//        this.name = name;
//        this.Location = location;
//        this.cuisineType = cuisineType;
//        this.menu = menu != null ? menu : new ArrayList<>(); // Initialize menu if it's null
//        this.openingTime = openingTime;
//        this.closingTime = closingTime;
//        this.isActive = isActive;
//        this.rating = rating;
//    }
    public Restaurant(long restaurantID, String name, String location, CuisineType cuisineType,
                      List<MenuItem> menu, LocalTime openingTime, LocalTime closingTime, boolean isActive) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.Location = location;
        this.cuisineType = cuisineType;
        this.menu = menu != null ? menu : new ArrayList<>(); // Initialize menu if it's null
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public long getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(long restaurantID) {
        this.restaurantID = restaurantID;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
