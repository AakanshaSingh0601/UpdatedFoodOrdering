package com.foodorderingapp.repository;
import com.foodorderingapp.model.CuisineType;
import com.foodorderingapp.model.MenuItem;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.util.JdbcConnectionUtil;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantRepoUsingDatabaseImpl implements RestaurantRepoUsingDatabase {

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) throws SQLException {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String query = "INSERT INTO restaurant (name, location, cuisinetype, openingtime, closingtime, isactive) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           // statement.setLong(1, restaurant.getRestaurantID());
            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getLocation());
            statement.setString(3, restaurant.getCuisineType().toString());
            statement.setTime(4, Time.valueOf(restaurant.getOpeningTime()));
            statement.setTime(5, Time.valueOf(restaurant.getClosingTime()));
            statement.setBoolean(6, restaurant.isActive());
            //statement.setDouble(8, restaurant.getRating());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating restaurant failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    restaurant.setRestaurantID(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating restaurant failed, no ID obtained.");
                }
            }
            return restaurant;
        }
    }

    @Override
    public Optional<Restaurant> getRestaurantById(int id) throws SQLException {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String query = "SELECT * FROM restaurant WHERE restaurantid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapResultSetToRestaurant(resultSet));
            }
            return Optional.empty();
        }
    }

//    @Override
//    public Optional<Restaurant> getRestaurantById(long id) throws SQLException {
//        try (Connection connection = JdbcConnectionUtil.getConnection()) {
//            String query = "SELECT * FROM restaurant WHERE restaurantid = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return Optional.of(mapResultSetToRestaurant(resultSet));
//            }
//            return Optional.empty();
//        }
//    }

    @Override
    public Optional<Restaurant> getRestaurantByLocation(String location) throws SQLException {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String query = "SELECT * FROM restaurant WHERE location = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, location);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapResultSetToRestaurant(resultSet));
            }
            return Optional.empty();
        }
    }

    @Override
    public Optional<Restaurant> getRestaurantByName(String name) throws SQLException {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String query = "SELECT * FROM restaurant WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapResultSetToRestaurant(resultSet));
            }
            return Optional.empty();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() throws SQLException {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String query = "SELECT * FROM restaurant";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Restaurant> restaurants = new ArrayList<>();
            while (resultSet.next()) {
                restaurants.add(mapResultSetToRestaurant(resultSet));
            }
            return restaurants;
        }
    }

    @Override
    public List<Restaurant> getAllActiveRestaurants() throws SQLException {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String query = "SELECT * FROM restaurant WHERE isactive = 1";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Restaurant> restaurants = new ArrayList<>();
            while (resultSet.next()) {
                restaurants.add(mapResultSetToRestaurant(resultSet));
            }
            return restaurants;
        }
    }

    @Override
    public List<Restaurant> getAllDeActiveRestaurants() throws SQLException {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String query = "SELECT * FROM restaurant WHERE isactive = 0";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Restaurant> restaurants = new ArrayList<>();
            while (resultSet.next()) {
                restaurants.add(mapResultSetToRestaurant(resultSet));
            }
            return restaurants;
        }
    }

    @Override
    public Optional<Restaurant> updateActivationStatus(Restaurant restaurant) throws SQLException {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String query = "UPDATE restaurant SET isactive = ? WHERE restaurantid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, restaurant.isActive());
            statement.setLong(2, restaurant.getRestaurantID());
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                return Optional.of(restaurant);
            }
            return Optional.empty();
        }
    }


    @Override
    public Optional<Long> deleteRestaurant(long id) throws SQLException {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String deleteQuery = "DELETE FROM restaurant WHERE restaurantid = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            int deletedRows = statement.executeUpdate();
            if (deletedRows > 0) {
                return Optional.of(id);
            }
            return Optional.empty();
        }
    }





//    @Override
//    public Optional<Restaurant> deleteRestaurant(long id) throws SQLException {
//        try (Connection connection = JdbcConnectionUtil.getConnection()) {
//            String query = "DELETE FROM restaurant WHERE restaurantid = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setLong(1, id);
//            int deletedRows = statement.executeUpdate();
//            if (deletedRows > 0) {
//                return Optional.of(new Restaurant());
//            }
//            return Optional.empty();
//        }
//    }

//    private Restaurant mapResultSetToRestaurant(ResultSet resultSet) throws SQLException {
//        long restaurantID = resultSet.getLong("restaurantid");
//        String name = resultSet.getString("name");
//        String location = resultSet.getString("location");
//        CuisineType cuisineType = CuisineType.valueOf(resultSet.getString("cuisinetype"));
//        LocalTime openingTime = resultSet.getTime("openingtime").toLocalTime();
//        LocalTime closingTime = resultSet.getTime("closingtime").toLocalTime();
//        boolean isActive = resultSet.getBoolean("isactive");
//        List<MenuItem> menu = getMenuItemsForRestaurant(restaurantID); // Fetch menu items from database
//        return new Restaurant(restaurantID, name, location, cuisineType, menu, openingTime, closingTime, isActive);
//    }

    private Restaurant mapResultSetToRestaurant(ResultSet resultSet) throws SQLException {
        long restaurantID = resultSet.getLong("restaurantid");
        String name = resultSet.getString("name");
        String location = resultSet.getString("location");
        CuisineType cuisineType = CuisineType.valueOf(resultSet.getString("cuisinetype").toUpperCase().replace(' ', '_'));
        LocalTime openingTime = resultSet.getTime("openingtime").toLocalTime();
        LocalTime closingTime = resultSet.getTime("closingtime").toLocalTime();
        boolean isActive = resultSet.getBoolean("isactive");
        List<MenuItem> menu = getMenuItemsForRestaurant(restaurantID); // Fetch menu items from database
        return new Restaurant(restaurantID, name, location, cuisineType, menu, openingTime, closingTime, isActive);
    }


    private List<MenuItem> getMenuItemsForRestaurant(long restaurantID) throws SQLException {
        List<MenuItem> menuItems = new ArrayList<>();
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            String query = "SELECT * FROM menuitem WHERE restaurantid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, restaurantID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Construct MenuItem objects based on the result set and add them to the list
              //  long menuItemId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                // Other properties...
                // Construct MenuItem object and add it to the list
                MenuItem menuItem = new MenuItem(name , price);
                menuItems.add(menuItem);
            }
        }
        return menuItems;
    }



}
