package com.kafka.demo.topic;

import com.kafka.demo.models.BeerOrder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC;

    public Producer(KafkaTemplate<String, String> kafkaTemplate,
                    @Value("${beerOrder.topic-name}") String TOPIC) {
        this.kafkaTemplate = kafkaTemplate;
        this.TOPIC = TOPIC;
    }

    public void sendMessage(BeerOrder beerOrder) {
        logger.info(String.format("#### -> Producing message of order -> %s", beerOrder.getIdOrder()));
        this.kafkaTemplate.send(TOPIC, beerOrder.toString());
        this.kafkaTemplate.flush();
    }


}
