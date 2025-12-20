package com.app.order_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.app.order_service.service.OrderStateManager;

@Component
public class PaymentEventConsumer {

    private final OrderStateManager orderStateManager;

    public PaymentEventConsumer(OrderStateManager orderStateManager) {
        this.orderStateManager = orderStateManager;
    }

    @KafkaListener(
            topics = "payment-events",
            groupId = "order-service-group"
    )
    public void consume(String message) {
        orderStateManager.handlePaymentEvent(message);
    }
}
