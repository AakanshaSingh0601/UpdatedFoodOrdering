package com.foodorderingapp.model;

import java.util.List;

public class DeliveryAgent {
    private long agentId;
    private String name;
    private String phoneNumber;
    private List<Order> assignedOrders;

    public DeliveryAgent(long agentId, String name, String phoneNumber, List<Order> assignedOrders) {
        this.agentId = agentId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.assignedOrders = assignedOrders;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getAssignedOrders() {
        return assignedOrders;
    }

    public void setAssignedOrders(List<Order> assignedOrders) {
        this.assignedOrders = assignedOrders;
    }

    public Long getId() {
        return agentId;
    }
}
