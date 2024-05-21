package com.foodorderingapp.service;

import com.foodorderingapp.model.Cart;
import com.foodorderingapp.model.Order;
import com.foodorderingapp.model.OrderStatus;
import com.foodorderingapp.repository.OrderRepository;
import com.foodorderingapp.repository.OrderRepositoryImpl;

import java.util.List;

public interface OrderService {
    Order getOrderByRepository(long orderId);
    void addToCart(long customerId, long menuItemId, int quantity, String specialInstructions);
    void removeFromCart(long customerId, long cartItemId);
    void placeOrder(long customerId, String deliveryAddress, String cardNumber, String expiryDate, String cvv);
    void updateOrderStatus(long deliveryAgentId, long orderId, OrderStatus status);
    List<Order> getOrdersByDeliveryAgent(long deliveryAgentId);
    List<Order> getOrdersByCustomerId(long customerId);
}
