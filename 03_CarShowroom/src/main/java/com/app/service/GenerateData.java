package com.app.service;

import com.app.converters.CarJsonConverter;
import com.app.model.*;

import java.math.BigDecimal;
import java.util.*;

public class GenerateData {
    static Set<Car> data() {
        Car car1 = Car.builder()
                .model("AUDI")
                .price(new BigDecimal(220000))
                .mileage(1000)
                .carBody(CarBody.builder()
                        .type(CarBodyType.HATCHBACK)
                        .components(new HashSet<>(Arrays.asList("AAA", "CCC", "DDD", "EEE")))
                        .color(CarBodyColor.GREEN)
                        .build())
                .engine(Engine.builder()
                        .power(259)
                        .type(EngineType.GASOLINE)
                        .build())
                .wheel(Wheel.builder()
                        .size(21)
                        .model("ASD")
                        .type(TyreType.SUMMER)
                        .build())
                .build();

        Car car2 = Car.builder()
                .model("BMW")
                .price(new BigDecimal(320000))
                .mileage(2000)
                .carBody(CarBody.builder()
                        .type(CarBodyType.COMBI)
                        .components(new HashSet<>(Arrays.asList("AAA", "BBB", "CCC", "DDD")))
                        .color(CarBodyColor.BLACK)
                        .build())
                .engine(Engine.builder()
                        .power(120)
                        .type(EngineType.LPG)
                        .build())
                .wheel(Wheel.builder()
                        .size(31)
                        .model("DUNLOP")
                        .type(TyreType.WINTER)
                        .build())
                .build();

        Car car3 = Car.builder()
                .model("KIA")
                .price(new BigDecimal(429000))
                .mileage(3000)
                .carBody(CarBody.builder()
                        .type(CarBodyType.SEDAN)
                        .components(new HashSet<>(Arrays.asList("AAA")))
                        .color(CarBodyColor.BLACK)
                        .build())
                .engine(Engine.builder()
                        .power(196)
                        .type(EngineType.DIESEL)
                        .build())
                .wheel(Wheel.builder()
                        .size(19)
                        .model("MICHELIN")
                        .type(TyreType.SUMMER)
                        .build())
                .build();

        Car car4 = Car.builder()
                .model("MERCEDES")
                .price(new BigDecimal(100000))
                .mileage(0)
                .carBody(CarBody.builder()
                        .type(CarBodyType.SEDAN)
                        .components(new HashSet<>(Arrays.asList("CCC", "DDD", "EEE")))
                        .color(CarBodyColor.BLUE)
                        .build())
                .engine(Engine.builder()
                        .power(326)
                        .type(EngineType.DIESEL)
                        .build())
                .wheel(Wheel.builder()
                        .size(20)
                        .model("DEBICA")
                        .type(TyreType.WINTER)
                        .build())
                .build();

        Car car5 = Car.builder()
                .model("AUDI")
                .price(new BigDecimal(113000))
                .mileage(0)
                .carBody(CarBody.builder()
                        .type(CarBodyType.COMBI)
                        .components(new HashSet<>(Arrays.asList("AAA", "DDD", "EEE")))
                        .color(CarBodyColor.WHITE)
                        .build())
                .engine(Engine.builder()
                        .power(198)
                        .type(EngineType.LPG)
                        .build())
                .wheel(Wheel.builder()
                        .size(23)
                        .model("MICHELIN")
                        .type(TyreType.WINTER)
                        .build())
                .build();

        return new LinkedHashSet<>(Arrays.asList(car1, car2, car3, car4, car5));


    }

    public static void generateData() {
        int i = 0;
        for (Car c : data()) {
            CarJsonConverter carJsonConverter = new CarJsonConverter("CAR" + i++ + "_" + c.getModel() + ".json");
            carJsonConverter.toJson(c);
        }
    }

    static Set<String> getGeneratedData() {
        Set<String> files = new HashSet<>();
        int i = 0;
        for (Car c : data()) {
            String file = "CAR" + i++ + "_" + c.getModel() + ".json";
            files.add(file);
        }
        return files;
    }


}
