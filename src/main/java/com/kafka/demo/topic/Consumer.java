package com.kafka.demo.topic;

import com.kafka.demo.models.BeerOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "${beerOrder.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(BeerOrder beerOrder) {
        logger.info(String.format("#### -> Consumed message -> %s", beerOrder.getIdOrder()));
        //TODO: Tratamento de recebimento de mensagens
    }
}
