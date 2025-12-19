package com.app.order_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.order_service.event.OrderCreatedEvent;
import com.app.order_service.producer.OrderEventProducer;
import com.app.order_service.model.Order;

@Service
public class OrderService {

    private final OrderEventProducer orderEventProducer;

    public OrderService(OrderEventProducer orderEventProducer) {
        this.orderEventProducer = orderEventProducer;
    }

    public void createOrder(String userId, double amount, String currency) {

        String orderId = UUID.randomUUID().toString();

        Order order = new Order(orderId, userId, amount, currency);

        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getOrderId(),
                order.getUserId(),
                order.getAmount(),
                order.getCurrency()
        );

        orderEventProducer.publishOrderCreatedEvent(event);

        

    }
    
}
