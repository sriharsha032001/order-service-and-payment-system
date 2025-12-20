package com.payment_service.payment_service.service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.java.com.payment_service.payment_service.event.PaymentFailedEvent;
import main.java.com.payment_service.payment_service.event.PaymentSuccessEvent;
import main.java.com.payment_service.payment_service.producer.PaymentEventProducer;

@Service
public class PaymentService {
        private static final int MAX_RETRY = 2;

        private final ExecutorService executorService;
        private final PaymentEventProducer paymentEventProducer;
        private final Random random = new Random();


        public PaymentService(ExecutorService executorService, PaymentEventProducer paymentEventProducer) {
            this.executorService = Executors.newFixedThreadPool(100);
            this.paymentEventProducer = paymentEventProducer;
        }

        public void handleOrderEvent(String orderEventMessage) {
        executorService.submit(() -> processPayment(orderEventMessage, 0));
    }

    private void processPayment(String orderEventMessage, int retryCount) {
        try {
            // Simulate external processing latency
            Thread.sleep(200);

            boolean paymentSuccess = random.nextBoolean();

            if (paymentSuccess) {
                PaymentSuccessEvent successEvent =
                        new PaymentSuccessEvent(
                                extractOrderId(orderEventMessage),
                                100.0,
                                "INR",
                                UUID.randomUUID().toString()
                        );

                producer.publishPaymentSuccess(successEvent);
            } else {
                handleFailure(orderEventMessage, retryCount, "Payment gateway failure");
            }

        } catch (Exception ex) {
            handleFailure(orderEventMessage, retryCount, ex.getMessage());
        }
    }
    private void handleFailure(String message, int retryCount, String reason) {
        if (retryCount < MAX_RETRY) {
            processPayment(message, retryCount + 1);
        } else {
            PaymentFailedEvent failedEvent =
                    new PaymentFailedEvent(
                            extractOrderId(message),
                            reason,
                            retryCount,
                            false
                    );

            producer.publishPaymentFailure(failedEvent);
        }
    }
    private String extractOrderId(String message) {
        return message; // temporary
    }
}
