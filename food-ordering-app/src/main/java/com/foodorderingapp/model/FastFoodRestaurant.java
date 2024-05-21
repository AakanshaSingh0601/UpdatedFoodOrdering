package com.foodorderingapp.model;

import java.time.LocalTime;
import java.util.List;

public class FastFoodRestaurant extends Restaurant {

    public FastFoodRestaurant(long restaurantID, String name, String location, CuisineType cuisineType, List<MenuItem> menu, LocalTime openingTime, LocalTime closingTime, boolean isActive) {
        super(restaurantID, name, location, cuisineType, menu, openingTime, closingTime, isActive);
    }
//
//    public FastFoodRestaurant(long id, String name, String location, LocalTime closeTime, LocalTime openTime, boolean status) {
//            super(id, name, location, CuisineType.FAST_FOOD_RESTAURANT, closeTime, openTime, status);
//        }

    }
//    public FastFoodRestaurant(long restaurantID, String name, String locationA, CuisineType cuisineType, List<MenuItem> menuItems, List<String> monday, List<String> saturday) {
//        super();
//    }
//
//    public FastFoodRestaurant(long restaurantID, String fastFoodRestaurant, String locationA, CuisineType cuisineType, List<MenuItem> menuItems, LocalTime of, LocalTime of1, boolean b, double v) {
//    }

