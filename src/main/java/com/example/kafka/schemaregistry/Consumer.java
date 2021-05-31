package com.example.kafka.schemaregistry;

import com.example.kafka.schemaregistry.MyCustomSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@EnableKafka
@Component
public class Consumer {
    @KafkaListener(topics = "${kafka.topicName}", groupId = "group_id")
    public void listen(@Payload ConsumerRecord<String, MyCustomSchema> message,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp) {
        System.out.println("Received: <key,value>: <" + message.key() + ", " + message.value() +"> " + timestamp);
    }
}
