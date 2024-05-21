package com.foodorderingapp.repository;

import com.foodorderingapp.model.*;

import java.util.*;

public class OrderRepositoryImpl implements OrderRepository {

    private Map<Long, Order> orders;
    private Map<Long, List<Order>> ordersByDeliveryAgent;
    private Map<Long, List<Order>> ordersByCustomer;
    private Map<Long, List<Order>> ordersByRestaurant;
    private Map<Long, Map<MenuItem, Integer>> customerCarts;

    private RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();

    public OrderRepositoryImpl() {
        this.orders = new HashMap<>();
        this.ordersByDeliveryAgent = new HashMap<>();
        this.ordersByCustomer = new HashMap<>();
        this.ordersByRestaurant = new HashMap<>();
        this.customerCarts = new HashMap<>();
    }

    @Override
    public void placeOrder(Order order) {
        orders.put(order.getOrderId(), order);
        addToMap(order.getDeliveryAgentId(), order, ordersByDeliveryAgent);
        addToMap(order.getCustomerId(), order, ordersByCustomer);
        addToMap(order.getRestaurantId(), order, ordersByRestaurant);
    }

    @Override
    public void updateOrderStatus(long orderId, Long id, OrderStatus status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(status);
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }


    public void updateOrderStatus(long orderId, OrderStatus status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(status);
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }


    public List<Order> getOrdersByDeliveryAgent(long deliveryAgentId) {
        return ordersByDeliveryAgent.getOrDefault(deliveryAgentId, new ArrayList<>());
    }


    public List<Order> getOrdersByCustomerId(long customerId) {
        return ordersByCustomer.getOrDefault(customerId, new ArrayList<>());
    }


    public List<Order> getOrdersByRestaurant(long restaurantId) {
        return ordersByRestaurant.getOrDefault(restaurantId, new ArrayList<>());
    }


    public void addToCart(Long customerId, long menuItemId, int quantity, String specialInstructions) {
        Map<MenuItem, Integer> cart = customerCarts.computeIfAbsent(customerId, k -> new HashMap<>());
        MenuItem menuItem = restaurantRepository.getMenuItemById(menuItemId); // Assuming this method exists
        if (menuItem != null) {
            cart.put(menuItem, cart.getOrDefault(menuItem, 0) + quantity);
        } else {
            throw new RuntimeException("Menu item not found with ID: " + menuItemId);
        }
    }


    public void placeOrder(long customerId, String deliveryAddress, String cardNumber, String expiryDate, String cvv) {
        Map<MenuItem, Integer> cart = customerCarts.get(customerId);
        if (cart == null || cart.isEmpty()) {
            throw new RuntimeException("Cart is empty for customer ID: " + customerId);
        }

        double totalAmount = cartValue(cart);
        Order order = new Order(generateOrderId(), customerId, cart, deliveryAddress, cardNumber, expiryDate, cvv, totalAmount);
        placeOrder(order);
        customerCarts.remove(customerId);
    }


    public Order getOrderById(long orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            return order;
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }


    public Order getOrderByRepository(long orderId) {
        return orders.get(orderId);
    }

    private void addToMap(long id, Order order, Map<Long, List<Order>> map) {
        map.computeIfAbsent(id, k -> new ArrayList<>()).add(order);
    }

    private double cartValue(Map<MenuItem, Integer> cart) {
        return cart.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private long generateOrderId() {
        return orders.size() + 1;
    }
}
