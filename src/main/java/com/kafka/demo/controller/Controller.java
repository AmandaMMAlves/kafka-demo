package com.kafka.demo.controller;

import com.kafka.demo.topic.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka/beerorder")
public class Controller {
    private final Producer producer;

    @PostMapping(value = "/publish")
    public void publishBeerOrder(@RequestParam String beerOrder){
        this.producer.sendMessage(beerOrder);
    }

}
