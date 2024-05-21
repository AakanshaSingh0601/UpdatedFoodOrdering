package com.foodorderingapp.repository;
import com.foodorderingapp.model.Restaurant;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepoUsingDatabase {
    public Restaurant saveRestaurant(Restaurant restaurant) throws SQLException;

    public Optional<Restaurant> getRestaurantById(int id) throws SQLException;

    public Optional<Restaurant> getRestaurantByLocation(String location) throws SQLException;

    public Optional<Restaurant> getRestaurantByName(String name) throws SQLException;

    public List<Restaurant> getAllRestaurants() throws SQLException;

    public List<Restaurant> getAllActiveRestaurants() throws SQLException;

    public List<Restaurant> getAllDeActiveRestaurants() throws SQLException;

    public Optional<Restaurant> updateActivationStatus(Restaurant restaurant) throws SQLException;

   // public Optional<Restaurant> deleteRestaurant(int id) throws SQLException;
    public  Optional<Long> deleteRestaurant(long id) throws SQLException;
}