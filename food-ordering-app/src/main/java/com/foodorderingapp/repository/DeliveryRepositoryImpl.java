package com.foodorderingapp.repository;

import com.foodorderingapp.exception.OrderNotFoundException;
import com.foodorderingapp.exception.DeliveryAgentNotFoundException;
import com.foodorderingapp.model.DeliveryAgent;
import com.foodorderingapp.model.Order;
import com.foodorderingapp.model.OrderStatus;

import java.util.*;

public class DeliveryRepositoryImpl implements DeliveryRepository {

    private Map<Long, DeliveryAgent> deliveryAgents;
    private Map<Long, List<Order>> ordersByDeliveryAgent;
    private Map<Long, Order> orders;

    public DeliveryRepositoryImpl() {
        this.deliveryAgents = new HashMap<>();
        this.ordersByDeliveryAgent = new HashMap<>();
        this.orders = new HashMap<>();
        init();
    }

    // Method to initialize some sample delivery agents
    public void init() {
        // Adding some sample delivery agents
        deliveryAgents.put(1L, new DeliveryAgent(1L, "Agent A", "123-456-7890", new ArrayList<>()));
        deliveryAgents.put(2L, new DeliveryAgent(2L, "Agent B", "123-456-7891", new ArrayList<>()));
        // Add more initial delivery agents as needed
    }


    public void assignOrderToDeliveryAgent(long orderId, long deliveryAgentId) {
        DeliveryAgent agent = deliveryAgents.get(deliveryAgentId);
        Order order = orders.get(orderId);

        if (agent == null) {
            throw new DeliveryAgentNotFoundException("Delivery agent not found with ID: " + deliveryAgentId);
        }
        if (order == null) {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }

        List<Order> agentOrders = ordersByDeliveryAgent.computeIfAbsent(deliveryAgentId, k -> new ArrayList<>());
        agentOrders.add(order);
        order.setDeliveryAgentId((int) deliveryAgentId);
        order.setStatus(OrderStatus.ASSIGNED);  // Assuming there's an ASSIGNED status
    }


    public List<Order> getOrdersByDeliveryAgent(long deliveryAgentId) {
        if (!deliveryAgents.containsKey(deliveryAgentId)) {
            throw new DeliveryAgentNotFoundException("Delivery agent not found with ID: " + deliveryAgentId);
        }
        return ordersByDeliveryAgent.getOrDefault(deliveryAgentId, new ArrayList<>());
    }


    public void updateDeliveryStatus(long orderId, OrderStatus status) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
        order.setStatus(status);
    }


    public void addDeliveryAgent(DeliveryAgent deliveryAgent) {
        deliveryAgents.put(deliveryAgent.getAgentId(), deliveryAgent);
    }

    @Override
    public void assignOrderToAgent(long orderId, long agentId) {
        assignOrderToDeliveryAgent(orderId, agentId);
    }

    @Override
    public void updateOrderStatus(long orderId, OrderStatus status) {
        updateDeliveryStatus(orderId, status);
    }

    @Override
    public List<Order> getPendingOrdersByAgent(long agentId) {
        return getOrdersByDeliveryAgent(agentId).stream()
                .filter(order -> order.getStatus() == OrderStatus.PENDING)  // Assuming there's a PENDING status
                .toList();
    }

    @Override
    public DeliveryAgent getDeliveryAgentById(long id) {
        DeliveryAgent agent = deliveryAgents.get(id);
        if (agent != null) {
            return agent;
        } else {
            throw new DeliveryAgentNotFoundException("Delivery agent not found with ID: " + id);
        }
    }

    @Override
    public Order getOrderByID(long orderID) {
        Order order = orders.get(orderID);
        if (order != null) {
            return order;
        } else {
            throw new OrderNotFoundException("Order not found with ID: " + orderID);
        }
    }

    public List<DeliveryAgent> getAllDeliveryAgents() {
        return new ArrayList<>(deliveryAgents.values());
    }


    public void updateOrderDetails(Order order) {
        orders.put(order.getOrderId(), order);
    }


    public void removeOrderFromDeliveryAgent(long orderId, long deliveryAgentId) {
        List<Order> agentOrders = ordersByDeliveryAgent.get(deliveryAgentId);
        if (agentOrders != null) {
            agentOrders.removeIf(order -> order.getOrderId() == orderId);
        }
    }
}
