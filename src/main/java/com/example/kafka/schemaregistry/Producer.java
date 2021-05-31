package com.example.kafka.schemaregistry;

import com.example.kafka.schemaregistry.MyCustomSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@EnableScheduling
@Component
public class Producer {

    @Value("${kafka.topicName}")
    private String topicName;

    @Autowired
    KafkaTemplate<String, MyCustomSchema> kafkaTemplate;

    private int iterator = 0;

    @Scheduled(fixedRate = 1300)
    public void sendMessages() {
        var currentTime = LocalDateTime.now();
        var myCustomSchema = new MyCustomSchema(
                iterator,
                "Iterator: " + String.valueOf(iterator),
                (float) currentTime.getSecond(),
                true,
                currentTime.toString());
        kafkaTemplate.send(topicName, "Some Key", myCustomSchema);
        iterator++;
    }

}


