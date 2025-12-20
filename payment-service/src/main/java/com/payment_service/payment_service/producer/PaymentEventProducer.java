package com.payment_service.payment_service.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import main.java.com.payment_service.payment_service.event.PaymentFailedEvent;
import main.java.com.payment_service.payment_service.event.PaymentSuccessEvent;

@Component
public class PaymentEventProducer {

    private static final String TOPIC = "payment-events";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentSuccess(PaymentSuccessEvent event) {
        kafkaTemplate.send(
                TOPIC,
                event.getAggregateId(),   // orderId as key
                event.toString()          // JSON later
        );
    }

    public void publishPaymentFailure(PaymentFailedEvent event) {
        kafkaTemplate.send(
                TOPIC,
                event.getAggregateId(),
                event.toString()
        );
    }
    
}