package com.app.service;

import com.app.converters.CarStoreJsonConverter;
import com.app.exceptions.MyException;
import com.app.model.Car;
import com.app.model.Cars;
import com.app.model.Color;
import com.app.service.enums.SortType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class CarService
{
    private Cars carsClass = new CarStoreJsonConverter("cars.json").fromJson().get();
    private Set<Car> cars = new HashSet<>(carsClass.getCars());

    List<Car> sortBy(SortType sortType, boolean descending)
    {
        Stream<Car> carStream = null;
        switch (sortType)
        {
            case MODEL:
                carStream = cars
                        .stream()
                        .sorted(Comparator.comparing(Car::getModel));
                break;
            case PRICE:
                carStream = cars
                        .stream()
                        .sorted(Comparator.comparing(Car::getPrice));
                break;
            case COLOR:
                carStream = cars
                        .stream()
                        .sorted(Comparator.comparing(Car::getColor));
                break;
            case MILEAGE:
                carStream = cars
                        .stream()
                        .sorted(Comparator.comparing(Car::getMileage));
        }

        List<Car> cars = carStream.collect(Collectors.toList());
        if (descending) {
            Collections.reverse(cars);
        }
        return cars;
    }
    List<Car> carsWithMileageGreaterThan(int mileage)
    {
        return cars
                .stream()
                .filter(car -> car.getMileage() > mileage)
                .collect(Collectors.toList());
    }
    Map<Color, Long> groupByColor()
    {
        return cars
                .stream()
                .collect(Collectors.groupingBy(
                            Car::getColor,
                            Collectors.counting()
                        )
                );
    }
    Map<String, Optional<Car>> modelCarMap()
    {
        return cars
                .stream()
                .collect(Collectors.groupingBy(Car::getModel, Collectors.maxBy(Comparator.comparing(Car::getPrice))))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (v1, v2) -> v1,
                            LinkedHashMap::new
                        )
                );
    }
    List<Car> carsWithPriceInRange(BigDecimal a, BigDecimal b)
    {
        return cars
                .stream()
                .filter(car -> car.getPrice().doubleValue() > a.doubleValue() && car.getPrice().doubleValue() < b.doubleValue())
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }
    void statistics()
    {
        DoubleSummaryStatistics issM = cars
                .stream()
                .map(car -> car.getMileage())
                .collect(Collectors.summarizingDouble(i -> i));
        System.out.println("max mileage : " + issM.getMax());
        System.out.println("min mileage : " + issM.getMin());
        System.out.println("avg mileage : " + Math.round(issM.getAverage()));

        BigDecimalSummaryStatistics issP = cars
                .stream()
                .map(car -> car.getPrice())
                .collect(Collectors2.summarizingBigDecimal(i -> i));

        System.out.println("\nmax price : " + issP.getMax());
        System.out.println("min price : " + issP.getMin());
        System.out.println("avg price : " + issP.getAverage().round(MathContext.DECIMAL32));
    }
    Optional<Car> theMostExpensiveCar()
    {
        return cars
                .stream()
                .max(Comparator.comparing(Car::getPrice));
    }
    void insertCar(Car car)
    {
        if (!car.getModel().matches("[A-Z ]+") || car.getMileage() <= 0 || car.getPrice().compareTo(BigDecimal.ZERO) <= 0 || car.getColor() == null || car.getEquipment().size() == 0)
        {
            throw new MyException("INSERT CAR - CAR OBJECT IS NULL");
        }
        cars.add(car);
    }
    void backToOriginalCarList()
    {
        cars.clear();
        cars.addAll(carsClass.getCars());
    }
    @Override
    public String toString() {
        return cars
                .stream()
                .map(Car::toString)
                .collect(Collectors.joining("\n"));
    }
}
