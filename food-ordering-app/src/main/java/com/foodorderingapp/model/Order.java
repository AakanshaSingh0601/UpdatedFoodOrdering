package com.foodorderingapp.model;

import java.util.Map;

public class Order {
    private int orderId;
    private int deliveryAgentId;
    private int customerId;
    private int restaurantId;
    private Cart cart;
    private String deliveryAddress;
    private PaymentDetails paymentDetails;
    private OrderStatus status;

    public Order(int orderId, int deliveryAgentId, int customerId, int restaurantId, Cart cart, String deliveryAddress, PaymentDetails paymentDetails, OrderStatus status) {
        this.orderId = orderId;
        this.deliveryAgentId = deliveryAgentId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.cart = cart;
        this.deliveryAddress = deliveryAddress;
        this.paymentDetails = paymentDetails;
        this.status = status;
    }

    public Order(long customerId, long id, Map<MenuItem, Integer> cart, String deliveryAddress, String cardNumber, String expiryDate, String cvv, double totalAmount) {
    }

    public Long getOrderId() {
        return Long.valueOf(orderId);
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDeliveryAgentId() {
        return deliveryAgentId;
    }

    public void setDeliveryAgentId(int deliveryAgentId) {
        this.deliveryAgentId = deliveryAgentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setDeliveryAgent(DeliveryAgent agent) {
    }




}

