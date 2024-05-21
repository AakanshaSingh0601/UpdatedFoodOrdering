package com.foodorderingapp;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;

import com.foodorderingapp.model.CuisineType;
import com.foodorderingapp.model.MenuItem;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.repository.RestaurantRepoUsingDatabase;
import com.foodorderingapp.repository.RestaurantRepoUsingDatabaseImpl;

public class RestaurantApplication {

    public static void main(String[] args) {
        RestaurantRepoUsingDatabase restaurantRepo = new RestaurantRepoUsingDatabaseImpl();
        Scanner scanner = new Scanner(System.in);

        // Save a new restaurant with user input
        //saveNewRestaurantWithInput(restaurantRepo, scanner);

        // Save a new restaurant
       // saveNewRestaurant(restaurantRepo);

        // Get a restaurant by ID
//        getRestaurantById(restaurantRepo);

        // Get a restaurant by name
         //getRestaurantByName(restaurantRepo);

        // Get a restaurant by location
        // getRestaurantByLocation(restaurantRepo);

        // Get all restaurants
        getAllRestaurants(restaurantRepo);

        // Get all active restaurants
        // getAllActiveRestaurants(restaurantRepo);

        // Get all inactive restaurants
        //getAllInactiveRestaurants(restaurantRepo);

        // Update activation status of a restaurant
        //updateRestaurantActivationStatus(restaurantRepo);

        // Delete a restaurant
        // deleteRestaurant(restaurantRepo);

        scanner.close();
    }
    private static void saveNewRestaurantWithInput(RestaurantRepoUsingDatabase restaurantRepo, Scanner scanner) {
        try {
            System.out.print("Enter restaurant name: ");
            String name = scanner.nextLine();
            System.out.print("Enter restaurant location: ");
            String location = scanner.nextLine();
            System.out.print("Enter cuisine type: ");
            String cuisineTypeStr = scanner.nextLine();
            CuisineType cuisineType = CuisineType.FINE_DINING;
            System.out.print("Enter opening time (HH:MM): ");
            LocalTime openingTime = LocalTime.parse(scanner.nextLine());
            System.out.print("Enter closing time (HH:MM): ");
            LocalTime closingTime = LocalTime.parse(scanner.nextLine());
            System.out.print("Is the restaurant active (true/false): ");
            boolean isActive = Boolean.parseBoolean(scanner.nextLine());

            Restaurant restaurant = new Restaurant(0, name, location, cuisineType, null, openingTime, closingTime, isActive);
            restaurantRepo.saveRestaurant(restaurant);
            System.out.println("Restaurant saved successfully with ID: " + restaurant.getRestaurantID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void saveNewRestaurant(RestaurantRepoUsingDatabase restaurantRepo) {
        try {
            Restaurant restaurant = new Restaurant(0, "Dominos", "Indore", CuisineType.FAST_FOOD, null,
                    LocalTime.of(8, 0), LocalTime.of(20, 0), true);
            restaurantRepo.saveRestaurant(restaurant);
            System.out.println("Restaurant saved successfully with ID: " + restaurant.getRestaurantID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getRestaurantById(RestaurantRepoUsingDatabase restaurantRepo) {
        try {
            Optional<Restaurant> optionalRestaurant = restaurantRepo.getRestaurantById(2);
            if (optionalRestaurant.isPresent()) {
                Restaurant restaurant = optionalRestaurant.get();
                printRestaurantDetails(restaurant);
            } else {
                System.out.println("Restaurant not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printRestaurantDetails(Restaurant restaurant) {
        System.out.println("Restaurant ID: " + restaurant.getRestaurantID());
        System.out.println("Name: " + restaurant.getName());
        System.out.println("Location: " + restaurant.getLocation());
        System.out.println("Cuisine Type: " + restaurant.getCuisineType());
        System.out.println("Opening Time: " + restaurant.getOpeningTime());
        System.out.println("Closing Time: " + restaurant.getClosingTime());
        System.out.println("Active: " + restaurant.isActive());
        System.out.println("Menu Items:");
        for (MenuItem menuItem : restaurant.getMenu()) {
            System.out.println("  - " + menuItem.getName() + " (ID: " + menuItem.getMenuItemId() + ", Price: " + menuItem.getPrice() + ")");
        }
    }

    private static void getRestaurantByName(RestaurantRepoUsingDatabase restaurantRepo) {
        try {
            Optional<Restaurant> optionalRestaurant = restaurantRepo.getRestaurantByName("Hotel Kanchal Tilak");
            if (optionalRestaurant.isPresent()) {
                Restaurant restaurant = optionalRestaurant.get();
                printRestaurantDetails(restaurant);
            } else {
                System.out.println("Restaurant not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getRestaurantByLocation(RestaurantRepoUsingDatabase restaurantRepo) {
        try {
            Optional<Restaurant> optionalRestaurant = restaurantRepo.getRestaurantByLocation("123 Main St");
            if (optionalRestaurant.isPresent()) {
                Restaurant restaurant = optionalRestaurant.get();
                printRestaurantDetails(restaurant);
            } else {
                System.out.println("Restaurant not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getAllRestaurants(RestaurantRepoUsingDatabase restaurantRepo) {
        try {
            System.out.println("All Restaurants:");
            restaurantRepo.getAllRestaurants().forEach(RestaurantApplication::printRestaurantDetails);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getAllActiveRestaurants(RestaurantRepoUsingDatabase restaurantRepo) {
        try {
            System.out.println("Active Restaurants:");
            restaurantRepo.getAllActiveRestaurants().forEach(RestaurantApplication::printRestaurantDetails);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getAllInactiveRestaurants(RestaurantRepoUsingDatabase restaurantRepo) {
        try {
            System.out.println("Inactive Restaurants:");
            restaurantRepo.getAllDeActiveRestaurants().forEach(RestaurantApplication::printRestaurantDetails);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateRestaurantActivationStatus(RestaurantRepoUsingDatabase restaurantRepo) {
        try {
            Optional<Restaurant> optionalRestaurant = restaurantRepo.getRestaurantById(2);
            if (optionalRestaurant.isPresent()) {
                Restaurant restaurant = optionalRestaurant.get();
                boolean newStatus = !restaurant.isActive();
                restaurant.setActive(newStatus);
                restaurantRepo.updateActivationStatus(restaurant);
                System.out.println("Restaurant activation status updated successfully.");
            } else {
                System.out.println("Restaurant not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteRestaurant(RestaurantRepoUsingDatabase restaurantRepo) {
        try {
            Optional<Restaurant> optionalRestaurant = restaurantRepo.getRestaurantById(1);
            if (optionalRestaurant.isPresent()) {
                Optional<Long> deletedRestaurantId = restaurantRepo.deleteRestaurant(1);
                if (deletedRestaurantId.isPresent()) {
                    System.out.println("Restaurant with ID " + deletedRestaurantId.get() + " deleted successfully.");
                } else {
                    System.out.println("Restaurant deletion failed.");
                }
            } else {
                System.out.println("Restaurant not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
