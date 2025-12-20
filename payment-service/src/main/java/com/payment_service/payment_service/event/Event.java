package main.java.com.payment_service.payment_service.event;

public interface Event {

    String getEventId();

    String getAggregateId(); // orderId

    long getTimestamp();
}