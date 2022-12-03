package com.example.fgocalculation.versatile;

import java.util.Objects;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculationSub {
    public double nullToZero(Double value) {
        if (Objects.isNull(value)) {
            value = 0.0;
        }
        return value;
    }
    
    public int nullToZero(Integer value) {
        if (Objects.isNull(value)) {
            value = 0;
        }
        return value;
    }
}
