package main.java.com.payment_service.payment_service.service;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    private void processPayment(String message, int retryCount) {
        try {
            // Simulate processing time
            Thread.sleep(200);

            boolean paymentSuccess = random.nextBoolean();

            if (paymentSuccess) {
                producer.publishPaymentSuccess(message);
            } else {
                handleFailure(message, retryCount);
            }

        } catch (Exception ex) {
            handleFailure(message, retryCount);
        }
    }

    private void handleFailure(String message, int retryCount) {
        if (retryCount < MAX_RETRY) {
            processPayment(message, retryCount + 1);
        } else {
            paymentEventProducer.publishPaymentFailure(message);
        }
    }
}
