package com.payment_service.payment_service.event;

import java.util.Objects;
import java.util.UUID;

public final class PaymentFailedEvent implements Event {

    private final String eventId;
    private final String orderId;
    private final String failureReason;
    private final int retryCount;
    private final boolean retryable;
    private final long timestamp;

    public PaymentFailedEvent(
            String orderId,
            String failureReason,
            int retryCount,
            boolean retryable
    ) {
        this.eventId = UUID.randomUUID().toString();
        this.orderId = Objects.requireNonNull(orderId, "orderId cannot be null");
        this.failureReason = Objects.requireNonNull(failureReason, "failureReason cannot be null");
        this.retryCount = retryCount;
        this.retryable = retryable;
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

    public String getFailureReason() {
        return failureReason;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public boolean isRetryable() {
        return retryable;
    }
}
