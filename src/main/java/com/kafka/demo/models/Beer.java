package com.kafka.demo.models;

import lombok.Data;

@Data
public class Beer {
    private final String idBeer;
    private final String name;
    private final Double price;
}
