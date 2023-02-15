package com.kafka.demo.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerDistribuicao {

    private final Logger logger = LoggerFactory.getLogger(ConsumerDistribuicao.class);

    @KafkaListener(topics = "${beerOrder.topic-name}", containerFactory = "distributionKafkaListenerContainerFactory")
    public void consumer(String beerOrder) {
        logger.info(String.format("#### -> WIIIIIII -> %s", beerOrder));
        //TODO: Tratamento de recebimento de mensagens
    }
}
