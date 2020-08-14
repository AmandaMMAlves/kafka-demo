package com.kafka.demo.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@RequiredArgsConstructor
public class TopicConfig {

    private final String TOPIC;

    public TopicConfig(@Value("${beerOrder.topic-name}") String TOPIC){
        this.TOPIC = TOPIC;
    }

    @Bean
    public NewTopic beerTopic(){
        return new NewTopic(TOPIC, 3, (short) 1);
    }
}

