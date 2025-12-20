package com.app.order_service.model;

public enum OrderState {
    CREATED,
    PAYMENT_IN_PROGRESS,
    PAID,
    FAILED,
    CANCELLED
}