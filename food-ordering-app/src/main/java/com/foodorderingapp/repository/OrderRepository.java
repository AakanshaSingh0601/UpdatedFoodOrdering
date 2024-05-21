package com.foodorderingapp.repository;

import com.foodorderingapp.model.Order;
import com.foodorderingapp.model.OrderStatus;

import java.util.List;

public interface OrderRepository {


    Order getOrderById(long orderId);


    Order getOrderByRepository(long orderId);
    void placeOrder(Order order);
    void updateOrderStatus(long orderId, Long id, OrderStatus status);
    List<Order> getOrdersByDeliveryAgent(long deliveryAgentId);
    List<Order> getOrdersByCustomerId(long customerId);

    void addToCart(Long customerId, long menuItemId, int quantity, String specialInstructions);


    void placeOrder(long l, String deliveryAddress, String cardNumber, String expiryDate, String cvv);
}
