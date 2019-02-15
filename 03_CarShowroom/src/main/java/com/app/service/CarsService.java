package com.app.service;
import com.app.exceptions.MyException;
import com.app.model.*;
import lombok.Data;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CarsService
{
    private final Cars carsClass = new Cars(new HashSet<>(Arrays.asList("AUDI.json","BMW.json","MERCEDES.json","BENTLEY.json")));
    private List<Car> cars = new ArrayList<>(carsClass.getCars());

    List<Car> sortCars(int choice, boolean descending)
    {
        Stream<Car> carStream = null;
        switch (choice)
        {
            case 1:
                carStream = cars
                        .stream()
                        .sorted(Comparator.comparingInt(o -> o.getCarBody().getComponents().size()));

            case 2:
                carStream = cars
                        .stream()
                        .sorted(Comparator.comparingInt(o -> o.getWheel().getSize()));
                break;
            case 3:
                carStream = cars
                        .stream()
                        .sorted(Comparator.comparingDouble(o -> o.getEngine().getPower()));
        }

        List<Car> cars = carStream.collect(Collectors.toList());
        if (descending == true)
        {
            Collections.reverse(cars);
        }
        return cars;
    }
    List<Car> findByCarBodyAndPriceRange(CarBodyColor carBodyColor, int min, int max)
    {
        return cars
                .stream()
                .filter(car -> car.getCarBody().getColor().equals(carBodyColor)
                        && car.getPrice().intValue() > min && car.getPrice().intValue() < max)
                .collect(Collectors.toList());
    }
    List<Car> sortedCarsWithEngineTypeAsArgument(EngineType engineType)
    {
        return cars
                .stream()
                .filter(car -> car.getEngine().getType().equals(engineType))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }
    void statistics(int choice)
    {
            switch (choice)
            {
                case 1:
                    BigDecimalSummaryStatistics issPrice = cars
                            .stream()
                            .map(car -> car.getPrice())
                            .collect(Collectors2.summarizingBigDecimal(i -> i));
                    System.out.println("max price : " + issPrice.getMax());
                    System.out.println("min price : " + issPrice.getMin() );
                    System.out.println("avg price : " + issPrice.getAverage().round(MathContext.DECIMAL32));
                    break;
                case 2:

                    DoubleSummaryStatistics issEnginePower = cars
                            .stream()
                            .map(car -> car.getEngine().getPower())
                            .collect(Collectors.summarizingDouble(e -> e));
                    System.out.println("max Engine Power : " + issEnginePower.getMax());
                    System.out.println("min Engine Power : " + issEnginePower.getMin() );
                    System.out.println("avg Engine Power : " + Math.round(issEnginePower.getAverage()));
                    break;
                case 3:
                    IntSummaryStatistics issMileage = cars
                            .stream()
                            .map(car -> car.getMileage())
                            .collect(Collectors.summarizingInt(i -> i));
                    System.out.println("max Mileage : " + issMileage.getMax());
                    System.out.println("min Mileage : " + issMileage.getMin() );
                    System.out.println("avg Mileage : " + Math.round(issMileage.getAverage()));
            }

    }
    Map<Car,Integer> carMileageMap()
    {
        return cars
                .stream()
                .collect(Collectors.toMap(car -> car, car -> car.getMileage()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(v1,v2) -> v1,LinkedHashMap::new));
    }
    Map<TyreType,List<Car>> carsWithTyreType()
    {
        return cars
                .stream()
                .collect(Collectors.groupingBy(car -> car.getWheel().getType(), Collectors.toList()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    List<Car> carsWithComponents(List<String> components)
    {
        return cars
                .stream()
                .filter(car -> car.getCarBody().getComponents().containsAll(components))
                .collect(Collectors.toList());
    }
    void backToOriginalCarList()
    {
       cars.clear();
       cars.addAll(carsClass.getCars());
    }
    void insertCar(Car car)
    {
        if (car.getModel().matches("[A-Z ]+") && car.getMileage() >= 0 && car.getPrice().intValue() > 0 && car.getEngine() != null && car.getCarBody() != null && car.getWheel() != null)
        {
            cars.add(car);
            System.out.println("Car has been added successfully !");
        }
        else if (car == null)
        {
            throw new MyException("INSERT CAR - CAR OBJECT IS NULL");
        }
        else throw new MyException("INSERT CAR - CAR ERROR");
    }
    void showAllCars()
    {
        cars.stream().forEach(System.out::println);
    }

}
