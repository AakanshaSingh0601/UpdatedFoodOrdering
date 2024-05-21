//package com.foodorderingapp.service;
//
//import com.foodorderingapp.model.DeliveryAgent;
//import com.foodorderingapp.model.Order;
//import com.foodorderingapp.model.OrderStatus;
//
//import java.util.List;
//
//public class DeliveryServiceImplementation implements DeliveryService {
//
//    private final DeliveryRepository deliveryRepository;
//    private static final DeliveryServiceImplementation instance = new DeliveryServiceImplementation();
//
//    private DeliveryServiceImplementation() {
//        this.deliveryRepository = new DeliveryRepositoryImpl();
//    }
//
//    public static DeliveryServiceImplementation getInstance() {
//        return instance;
//    }
//
//    @Override
//    public void assignOrderToAgent(long orderId, long agentId) {
//        deliveryRepository.assignOrderToAgent(orderId, agentId);
//    }
//
//    @Override
//    public void updateOrderStatus(long orderId, OrderStatus status) {
//        deliveryRepository.updateOrderStatus(orderId, status);
//    }
//
//    @Override
//    public List<Order> getPendingOrdersByAgent(long agentId) {
//        return deliveryRepository.getPendingOrdersByAgent(agentId);
//    }
//
//    @Override
//    public DeliveryAgent getDeliveryAgentById(long agentId) {
//        return deliveryRepository.getDeliveryAgentById(agentId);
//    }
//}
