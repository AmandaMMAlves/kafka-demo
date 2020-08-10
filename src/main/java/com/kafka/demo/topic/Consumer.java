package com.kafka.demo.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "${beerOrder.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String message) {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        //TODO: Tratamento de recebimento de mensagens
    }
}