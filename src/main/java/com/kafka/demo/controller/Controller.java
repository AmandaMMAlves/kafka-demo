package com.kafka.demo.controller;

import com.kafka.demo.topic.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka/beerorder")
public class Controller {
    private final Producer producer;

    @PostMapping(value = "/publish")
    public void publishBeerOrder(@RequestBody String beerOrder){
        this.producer.sendMessage(beerOrder);
    }

}
