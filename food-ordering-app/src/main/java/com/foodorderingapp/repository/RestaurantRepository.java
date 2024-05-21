package com.foodorderingapp.repository;

import com.foodorderingapp.model.*;

import java.util.List;
import java.util.Map;

public interface RestaurantRepository {
    MenuItem getMenuItemById(long menuItemId);
    void addRestaurant(Restaurant restaurant);
    void setActivationStatus(long restaurantId, boolean status);
    void updateMenuItem(Restaurant restaurant, MenuItem item);
    Restaurant getRestaurantById(long id);
    List<Restaurant> findRestaurantByLocation(String location);
    Restaurant findRestaurantByName(String name);
    List<Restaurant> findRestaurantByType(CuisineType type);
    List<Restaurant> findAllActiveRestaurant();
    List<Restaurant> findAllDeactivatedRestaurant();
    void order(int id, Customer customer, Map<MenuItem, Integer> cart, String address, String paymentType);
}
