package com.foodorderingapp.repository;

import com.foodorderingapp.exception.RestaurantNotFoundException;
import com.foodorderingapp.model.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestaurantRepositoryImpl implements RestaurantRepository {

    private Map<Long, Restaurant> restaurants;
    private Map<Long, MenuItem> menuItems;

    public RestaurantRepositoryImpl() {
        this.menuItems = new HashMap<>();
        this.restaurants = new HashMap<>();
        init();
    }

    // Method to initialize some sample restaurants and menu items
    private void init() {
        // Initialize some sample menu items
        menuItems.put(1L, new MenuItem("Burger", 5.99));
        menuItems.put(2L, new MenuItem("Pizza", 8.99));
        // Add more menu items as needed

        // Adding some sample restaurants
        restaurants.put(1L, new Restaurant(1L, "Desire Hotel", "SYDN", CuisineType.FAST_FOOD, new ArrayList<>(), LocalTime.now(), LocalTime.now(), true));
        restaurants.put(2L, new Restaurant(2L, "5star Hotel", "SYDN", CuisineType.FAST_FOOD, new ArrayList<>(), LocalTime.now(), LocalTime.now(), true));
        // Add more initial restaurants as needed
    }


    @Override
    public MenuItem getMenuItemById(long menuItemId) {
        return menuItems.get(menuItemId);
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getRestaurantID(), restaurant);
    }

    @Override
    public void setActivationStatus(long restaurantId, boolean status) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            restaurant.setActive(status);
        } else {
            throw new RestaurantNotFoundException("Restaurant not found with ID: " + restaurantId);
        }
    }

    @Override
    public void updateMenuItem(Restaurant restaurant, MenuItem item) {
        List<MenuItem> menu = restaurant.getMenu();
        if (menu == null) {
            menu = new ArrayList<>(); // Initialize menu if it's null
            restaurant.setMenu(menu);
        }
        for (MenuItem menuItem : menu) {
            if (menuItem.getName().equalsIgnoreCase(item.getName())) {
                menuItem.setPrice(item.getPrice());
                return;
            }
        }
        menu.add(item); // If item not found, add it to the menu
    }

    @Override
    public Restaurant getRestaurantById(long id) {
        Restaurant restaurant = restaurants.get(id);
        if (restaurant != null) {
            return restaurant;
        } else {
            throw new RestaurantNotFoundException("Restaurant not found with ID: " + id);
        }
    }

    @Override
    public List<Restaurant> findRestaurantByLocation(String location) {
        List<Restaurant> restaurantList = restaurants.values().stream()
                .filter(restaurant -> restaurant.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
        if (restaurantList.isEmpty()) {
            throw new RestaurantNotFoundException("Oops! No Restaurants found with Location: " + location);
        }
        return restaurantList;
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        List<Restaurant> restaurantList = restaurants.values().stream()
                .filter(restaurant -> restaurant.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        if (restaurantList.isEmpty()) {
            throw new RestaurantNotFoundException("Oops! No Restaurants found with Name: " + name);
        }
        return restaurantList.get(0); // Assuming there is only one restaurant with a given name
    }

    @Override
    public List<Restaurant> findRestaurantByType(CuisineType type) {
        List<Restaurant> restaurantList = restaurants.values().stream()
                .filter(restaurant -> restaurant.getCuisineType().equals(type))
                .collect(Collectors.toList());
        if (restaurantList.isEmpty()) {
            throw new RestaurantNotFoundException("Oops! No Restaurants found with Type: " + type);
        }
        return restaurantList;
    }

    @Override
    public List<Restaurant> findAllActiveRestaurant() {
        List<Restaurant> restaurantList = restaurants.values().stream()
                .filter(Restaurant::isActive)
                .collect(Collectors.toList());
        if (restaurantList.isEmpty()) {
            throw new RestaurantNotFoundException("Oops! All Restaurants are De-Active at this moment.");
        }
        return restaurantList;
    }

    @Override
    public List<Restaurant> findAllDeactivatedRestaurant() {
        List<Restaurant> restaurantList = restaurants.values().stream()
                .filter(restaurant -> !restaurant.isActive())
                .collect(Collectors.toList());
        if (restaurantList.isEmpty()) {
            throw new RestaurantNotFoundException("Oops! All Restaurants are Active");
        }
        return restaurantList;
    }

    @Override
    public void order(int id, Customer customer, Map<MenuItem, Integer> cart, String address, String paymentType) {
        // Implementation of order method
    }
}
