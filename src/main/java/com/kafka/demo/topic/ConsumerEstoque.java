package com.kafka.demo.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerEstoque {
    private final Logger logger = LoggerFactory.getLogger(ConsumerEstoque.class);

    @KafkaListener(topics = "${beerOrder.topic-name}", containerFactory = "stockKafkaListenerContainerFactory")
    public void consumerEstoque(String beerOrder) {
        logger.info(String.format("#### -> Consumed message -> %s", beerOrder));
        //TODO: Tratamento de recebimento de mensagens
    }
}
