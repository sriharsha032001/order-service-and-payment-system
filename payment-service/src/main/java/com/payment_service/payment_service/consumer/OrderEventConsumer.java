package com.payment_service.payment_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.payment_service.payment_service.service.PaymentService;
@Component
public class OrderEventConsumer {

    private final PaymentService paymentService;

      public OrderEventConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(
            topics = "order-events",
            groupId = "payment-service-group"
    )
    public void consume(String message) {

        // Rule 1: Do NOT process payment here
        // Rule 2: Do NOT block this thread
        // Rule 3: Delegate immediately

        paymentService.handleOrderEvent(message);
    }
    
}
