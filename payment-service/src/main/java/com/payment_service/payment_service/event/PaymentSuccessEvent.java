package com.payment_service.payment_service.event;

import java.util.Objects;
import java.util.UUID;

public final class PaymentSuccessEvent implements Event {

    private final String eventId;
    private final String orderId;
    private final double amount;
    private final String currency;
    private final String paymentReferenceId;
    private final long timestamp;

    public PaymentSuccessEvent(
            String orderId,
            double amount,
            String currency,
            String paymentReferenceId
    ) {
        this.eventId = UUID.randomUUID().toString();
        this.orderId = Objects.requireNonNull(orderId, "orderId cannot be null");
        this.amount = amount;
        this.currency = Objects.requireNonNull(currency, "currency cannot be null");
        this.paymentReferenceId = Objects.requireNonNull(paymentReferenceId, "paymentReferenceId cannot be null");
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

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentReferenceId() {
        return paymentReferenceId;
    }
}
