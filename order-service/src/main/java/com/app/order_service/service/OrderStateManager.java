package com.app.order_service.service;

import org.springframework.stereotype.Service;

import com.app.order_service.model.OrderState;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderStateManager {

    // In-memory state store (later DB / Redis)
    private final ConcurrentHashMap<String, OrderState> orderStateStore =
            new ConcurrentHashMap<>();

    public void handlePaymentEvent(String message) {

        String orderId = extractOrderId(message);

        if (isPaymentSuccess(message)) {
            updateState(orderId, OrderState.PAID);
        } else {
            updateState(orderId, OrderState.FAILED);
        }
    }

    private void updateState(String orderId, OrderState newState) {
        orderStateStore.put(orderId, newState);
        System.out.println("Order " + orderId + " moved to state " + newState);
    }

    // Temporary logic (will be JSON-based later)
    private boolean isPaymentSuccess(String message) {
        return message.contains("PaymentSuccessEvent");
    }

    private String extractOrderId(String message) {
        return message; // placeholder
    }
}

