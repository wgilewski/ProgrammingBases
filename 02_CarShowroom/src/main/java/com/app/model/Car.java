package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car
{
    private String model;
    private Color color;
    private BigDecimal price;
    private int mileage;
    private Set<String> equipment;

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color=" + color +
                ", price=" + price +
                ", mileage=" + mileage +
                ", equipment=" + equipment +
                '}';
    }
}
