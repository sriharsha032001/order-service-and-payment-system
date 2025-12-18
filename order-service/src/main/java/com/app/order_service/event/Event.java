package com.app.order_service.event;

public interface Event {

    String getEventId();
    String getAggregateId();
    long getTimestamp();
}
