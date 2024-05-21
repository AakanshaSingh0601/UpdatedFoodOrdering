//package com.foodorderingapp.service;
//
//import com.foodorderingapp.model.CuisineType;
//import com.foodorderingapp.model.MenuItem;
//import com.foodorderingapp.model.Restaurant;
//
//import java.util.List;
//
//public interface RestaurantService {
//
//    public void addRestaurant(Restaurant restaurant);
//
//    public void setActivationStatus(long restaurantId, boolean status);
//
//    public void updateMenuItem(MenuItem item);
//
//    public Restaurant getRestaurantById(long id);
//
//    public List<Restaurant> findRestaurantByLocation(String location);
//
//    public Restaurant findRestaurantByName(String name);
//
//    public List<Restaurant> findRestaurantByType(CuisineType type);
//
//    public List<Restaurant> findAllActiveRestaurant();
//
//    public List<Restaurant> findAllDeactivatedRestaurant();
//
//    //void updateRestaurant(Restaurant restaurant);
//
//    //void removeRestaurant(long restaurantId);
//
//    //List<Restaurant> getAllRestaurants();
//
//    public void order(int id, Customer customer, Map<MenuItem, Integer> cart, String address, String paymentType);
//}
