package com.app.order_service.model;

public class Order {

    private final String orderId;
    private final String userId;
    private final double amount;
    private final String currency;


    public Order(String orderId, String userId, double amount, String currency) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
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
