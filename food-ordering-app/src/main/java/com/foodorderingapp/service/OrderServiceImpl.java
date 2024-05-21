//package com.foodorderingapp.service;
//
//import com.foodorderingapp.model.Cart;
//import com.foodorderingapp.model.Order;
//import com.foodorderingapp.model.OrderStatus;
//import com.foodorderingapp.repository.OrderRepository;
//import com.foodorderingapp.repository.OrderRepositoryImpl;
//
//import java.util.List;
//
//public class OrderServiceImplementation implements OrderService {
//
//    private final OrderRepository orderRepository;
//
//    public OrderServiceImplementation() {
//        this.orderRepository = new OrderRepositoryImpl();
//    }
//
//    @Override
//    public Order getOrderByRepository(long orderId) {
//        return orderRepository.getOrderById(orderId);
//    }
//
//    @Override
//    public void addToCart(long customerId, long menuItemId, int quantity, String specialInstructions) {
//        orderRepository.addToCart(customerId, menuItemId, quantity, specialInstructions);
//    }
//
//    @Override
//    public void removeFromCart(long customerId, long cartItemId) {
//        // Implement this method in OrderRepositoryImpl and call here
//        orderRepository.removeFromCart(customerId, cartItemId);
//    }
//
//    @Override
//    public void placeOrder(long customerId, String deliveryAddress, String cardNumber, String expiryDate, String cvv) {
//        orderRepository.placeOrder(customerId, deliveryAddress, cardNumber, expiryDate, cvv);
//    }
//
//    @Override
//    public void updateOrderStatus(long deliveryAgentId, long orderId, OrderStatus status) {
//        orderRepository.updateOrderStatus(orderId, deliveryAgentId, status);
//    }
//
//    @Override
//    public List<Order> getOrdersByDeliveryAgent(long deliveryAgentId) {
//        return orderRepository.getOrdersByDeliveryAgent(deliveryAgentId);
//    }
//
//    @Override
//    public List<Order> getOrdersByCustomerId(long customerId) {
//        return orderRepository.getOrdersByCustomer(customerId);
//    }
//}
