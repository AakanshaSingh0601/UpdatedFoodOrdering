package com.foodorderingapp.service;

import com.foodorderingapp.model.DeliveryAgent;
import com.foodorderingapp.model.Order;
import com.foodorderingapp.model.OrderStatus;

import java.util.List;

public interface DeliveryService {
    void assignOrderToAgent(long orderId, long agentId);
    void updateOrderStatus(long orderId, OrderStatus status);
    List<Order> getPendingOrdersByAgent(long agentId);
    DeliveryAgent getDeliveryAgentById(long agentId);
    Order getOrderByID(long orderID);
}
