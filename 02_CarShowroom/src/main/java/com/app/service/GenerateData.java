package com.app.service;

import com.app.converters.CarStoreJsonConverter;
import com.app.model.Car;
import com.app.model.Cars;
import com.app.model.Color;

import java.math.BigDecimal;
import java.util.*;

public class GenerateData
{
    public static void generate()
    {
        Set<Car> carSet = new HashSet<>(Arrays.asList(
                Car.builder()
                        .model("AUDI")
                        .price(new BigDecimal(129000))
                        .mileage(120)
                        .color(Color.BLACK)
                        .equipment(new HashSet<>(Arrays.asList("AAA","BBB","DDD","EEE","FFF","GGG")))
                        .build(),

                Car.builder()
                        .model("AUDI")
                        .price(new BigDecimal(1229000))
                        .mileage(0)
                        .color(Color.BLACK)
                        .equipment(new HashSet<>(Arrays.asList("AAA","BBB")))
                        .build(),

                Car.builder()
                        .model("AUDI")
                        .price(new BigDecimal(987000))
                        .mileage(10)
                        .color(Color.BLACK)
                        .equipment(new HashSet<>(Arrays.asList("AAA","BBB","CCC","DDD","EEE","FFF","GGG")))
                        .build(),

                Car.builder()
                        .model("MERCEDES")
                        .price(new BigDecimal(229000))
                        .mileage(12)
                        .color(Color.SILVER)
                        .equipment(new HashSet<>(Arrays.asList("AAA","EEE","FFF","GGG")))
                        .build(),

                Car.builder()
                        .model("BMW")
                        .price(new BigDecimal(419000))
                        .mileage(1120)
                        .color(Color.BLACK)
                        .equipment(new HashSet<>(Arrays.asList("AAA","BBB","FFF","GGG")))
                        .build(),

                Car.builder()
                        .model("BENTLEY")
                        .price(new BigDecimal(890000))
                        .mileage(0)
                        .color(Color.BLUE)
                        .equipment(new HashSet<>(Arrays.asList("AAA","BBB","CCC","DDD","EEE")))
                        .build(),

                Car.builder()
                        .model("ASTON MARTIN")
                        .price(new BigDecimal(318000))
                        .mileage(120)
                        .color(Color.WHITE)
                        .equipment(new HashSet<>(Arrays.asList("BBB","CCC","DDD","EEE","FFF","GGG")))
                        .build()
        ));

        Cars cars = Cars.builder().cars(carSet).build();

        CarStoreJsonConverter carStoreJsonConverter = new CarStoreJsonConverter("cars.json");
        carStoreJsonConverter.toJson(cars);

    }
}
