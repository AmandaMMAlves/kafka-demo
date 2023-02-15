package com.kafka.demo.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Beer {
    private final String idBeer;
    private final String name;
    private final Double price;
}
