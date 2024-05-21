package com.foodorderingapp.exception;

public class DeliveryAgentNotFoundException extends RuntimeException{
    public DeliveryAgentNotFoundException(String message) {
        super(message);
    }
}
