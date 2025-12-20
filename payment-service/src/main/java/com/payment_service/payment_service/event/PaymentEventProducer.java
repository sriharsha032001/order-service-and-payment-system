package main.java.com.payment_service.payment_service.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private static final String TOPIC = "payment-events";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentSuccess(String message) {
        // key can later be orderId for partition ordering
        kafkaTemplate.send(TOPIC, "PAYMENT_SUCCESS", message);
    }

    public void publishPaymentFailure(String message) {
        kafkaTemplate.send(TOPIC, "PAYMENT_FAILED", message);
    }
    
}
