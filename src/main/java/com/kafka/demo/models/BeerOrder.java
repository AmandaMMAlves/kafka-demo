package com.kafka.demo.models;

import lombok.Data;

import java.util.List;

@Data
public class BeerOrder {
    private final String idOrder;
    private final Double discountPercent;
    private final List<Beer> order;
}
