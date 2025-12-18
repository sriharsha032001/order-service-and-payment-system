package com.app.order_service.event;

import java.util.Objects;
import java.util.UUID;

public final class OrderCreatedEvent implements Event{

    private final String eventId;
    private final String orderId;
    private final String userId;
    private final double amount;
    private final String currency;
    private final long timestamp;
    
     public OrderCreatedEvent(
            String orderId,
            String userId,
            double amount,
            String currency
    )
    {
        this.eventId = UUID.randomUUID().toString();
        this.orderId = Objects.requireNonNull(orderId, "orderId cannot be null");
        this.userId = Objects.requireNonNull(userId, "userId cannot be null");
        this.currency = Objects.requireNonNull(currency, "currency cannot be null");
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public String getAggregateId() {
        return orderId;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
    }
