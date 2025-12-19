package com.app.order_service.producer;

import org.springframework.stereotype.Component;

import com.app.order_service.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;


@Component
public class OrderEventProducer {

    private static final String TOPIC = "order-events";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderCreatedEvent(OrderCreatedEvent event) {

        String key = event.getAggregateId(); //order id
        kafkaTemplate.send(TOPIC, key, event.toString());
    }

}
