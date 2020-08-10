package com.kafka.demo.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PracticalAdvice {
    private final String message;
    private final int identifier;
}
