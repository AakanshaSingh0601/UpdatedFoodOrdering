package com.foodorderingapp.ui;

import java.time.LocalTime;
import java.util.List;

import com.foodorderingapp.model.CuisineType;
import com.foodorderingapp.model.MenuItem;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.repository.DeliveryRepository;
import com.foodorderingapp.repository.DeliveryRepositoryImpl;
import com.foodorderingapp.repository.OrderRepository;
import com.foodorderingapp.repository.OrderRepositoryImpl;
import com.foodorderingapp.repository.RestaurantRepository;
import com.foodorderingapp.repository.RestaurantRepositoryImpl;

public class Admin {
    public static void main(String[] args) {
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        DeliveryRepository deliveryRepository = new DeliveryRepositoryImpl();

        // Adding restaurants
        Restaurant restaurant1 = new Restaurant(101, "Manju Ka Tila", "Noida", CuisineType.FAST_FOOD,
                null, LocalTime.parse("09:00"), LocalTime.parse("19:00"), true);

        Restaurant restaurant2 = new Restaurant(102, "Bapu Ki Kutiya", "Indore", CuisineType.FINE_DINING,
                null, LocalTime.parse("09:00"), LocalTime.parse("20:00"), false);

        Restaurant restaurant3 = new Restaurant(103, "Desi Tadka", "Bangalore", CuisineType.FINE_DINING,
                null, LocalTime.parse("09:00"), LocalTime.parse("19:00"), true);

        // Adding items in restaurant1
        restaurantRepository.addRestaurant(restaurant1);
        restaurantRepository.updateMenuItem(restaurant1, new MenuItem("Noodles", 149.0));
        restaurantRepository.updateMenuItem(restaurant1, new MenuItem("Soup", 119.0));
        restaurantRepository.updateMenuItem(restaurant1, new MenuItem("Manchurian", 349.0));

        // Adding items in restaurant2
        restaurantRepository.addRestaurant(restaurant2);
        restaurantRepository.updateMenuItem(restaurant2, new MenuItem("Veg Biryani", 399.0));
        restaurantRepository.updateMenuItem(restaurant2, new MenuItem("Chilli Paneer", 249.0));
        restaurantRepository.updateMenuItem(restaurant2, new MenuItem("Butter Naan", 25.0));

        // Adding items in restaurant3
        restaurantRepository.addRestaurant(restaurant3);
        restaurantRepository.updateMenuItem(restaurant3, new MenuItem("Chinese Bhel", 199.0));
        restaurantRepository.updateMenuItem(restaurant3, new MenuItem("Paneer Roll", 299.0));

        // Finding all active restaurants
        List<Restaurant> restaurantList = restaurantRepository.findAllActiveRestaurant();
        System.out.println("Active Restaurants:");
        restaurantList.forEach(restaurant -> System.out.println(restaurant.getName()));

        // After deactivating and activating restaurants, finding active ones
        restaurantRepository.setActivationStatus(restaurant1.getRestaurantID(), true);
        restaurantRepository.setActivationStatus(restaurant2.getRestaurantID(), true);

        restaurantList = restaurantRepository.findAllActiveRestaurant();
        System.out.println("\nActive Restaurants:");
        restaurantList.forEach(restaurant -> {
            System.out.println(restaurant.getName());
            restaurant.getMenu().forEach(menuItem -> System.out.println(menuItem.getName() + " - " + menuItem.getPrice()));
        });

        // Finding restaurants by location
        String location = "Noida";
        restaurantList = restaurantRepository.findRestaurantByLocation(location);
        System.out.println("\nRestaurants in " + location + ":");
        restaurantList.forEach(restaurant -> System.out.println(restaurant.getName()));

        // Finding restaurants by type
        CuisineType type = CuisineType.FINE_DINING;
        restaurantList = restaurantRepository.findRestaurantByType(type);
        System.out.println("\nRestaurants of type " + type + ":");
        restaurantList.forEach(restaurant -> System.out.println(restaurant.getName()));
    }
}
