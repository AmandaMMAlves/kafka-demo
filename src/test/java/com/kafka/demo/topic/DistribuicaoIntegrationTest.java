package com.kafka.demo.topic;

import com.kafka.demo.models.Beer;
import com.kafka.demo.models.BeerOrder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(properties = {"spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class DistribuicaoIntegrationTest {

    private KafkaConsumer<String, String> consumer;

    @Autowired
    private Producer producer;

    private final String TOPIC_NAME = "beer-topic-test";

    @BeforeEach
    private void setUp(){
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "${spring.embedded.kafka.brokers}");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "distributionGroupId");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        consumer = new KafkaConsumer<String, String>(config);
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));
    }

    @Test
    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
            throws Exception {
        BeerOrder beerOrder = BeerOrder.builder()
                .idOrder("12345")
                .discountPercent(0.10)
                .order(Collections.singletonList(
                        Beer.builder()
                            .idBeer("001")
                            .name("Heineken")
                            .price(7.99).build())).build();

        producer.sendMessage(beerOrder);

//        producer.send(new ProducerRecord<>(TOPIC_NAME, beerOrder.getIdOrder(), beerOrder.toString()));

        HashMap<String, String> events = new HashMap<>();

        consumer.poll(Duration.ofMillis(20000)).forEach(consumerRecord ->
                events.put(consumerRecord.key(), consumerRecord.value()));

        Assertions.assertFalse(events.isEmpty());
    }

}