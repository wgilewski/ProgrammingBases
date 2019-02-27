package com.app.service;


import com.app.exceptions.MyException;
import com.app.model.Car;
import com.app.model.Color;
import com.app.service.enums.SortType;

import java.math.BigDecimal;
import java.util.*;

public class MenuService {

    private final CarService carService = new CarService();

    public void mainMenu() {
        while (true) {
            try {
                System.out.println("\n<----Menu---->");
                System.out.println("1. New car");
                System.out.println("2. Show all cars");
                System.out.println("3. Sort cars by");
                System.out.println("4. Select cars with mileage bigger than");
                System.out.println("5. Get color with car amount");
                System.out.println("6. The most expensive model");
                System.out.println("7. Get cars from price range");
                System.out.println("8. Statistics");
                System.out.println("9. The most expensive car");
                System.out.println("10. Back to original car list");
                System.out.println("11. Close");

                int option = UserDataService.getInt("\nPlease enter menu number : ");
                if (option < 1 || option > 11) {
                    System.err.println("PLEASE INSERT NUMBER FROM MENU");
                }

                switch (option) {
                    case 1:
                        insertCar();
                        break;


                    case 2:
                        carService.getCars().stream().forEach(System.out::println);
                        break;


                    case 3:
                        List<Car> sortedCars = sortedCars();
                        sortedCars.stream().forEach(System.out::println);
                        break;

                    case 4:
                        int mileage1;
                        do {
                            mileage1 = UserDataService.getInt("\nPlease enter mileage : ");
                        } while (mileage1 <= 0);
                        List<Car> carsWithMileageGreaterThan = carService.carsWithMileageGreaterThan(mileage1);
                        carsWithMileageGreaterThan.stream().forEach(System.out::println);
                        break;


                    case 5:
                        Map<Color, Long> colorCarMap = carService.groupByColor();
                        colorCarMap.entrySet().stream().forEach(System.out::println);
                        break;


                    case 6:
                        Map<String, Optional<Car>> map = carService.modelCarMap();
                        map.entrySet().stream().forEach(System.out::println);
                        break;


                    case 7:
                        List<Car> cars = getCarsFromPriceRange();
                        cars.stream().forEach(System.out::println);
                        break;


                    case 8:
                        carService.statistics();
                        break;


                    case 9:
                        Optional<Car> car = carService.theMostExpensiveCar();
                        if (car.isPresent()) {
                            System.out.println(car.get());
                        }
                        break;


                    case 10:
                        carService.backToOriginalCarList();
                        break;


                    case 11:
                        UserDataService.close();
                        return;
                }
            } catch (MyException e) {
                System.err.println(e.getExceptionMessage());
            }
        }
    }

    public List<Car> sortedCars() {
        int choice1;
        SortType[] sortTypes = SortType.values();
        do {
            System.out.println("\n\n1. Sort by price");
            System.out.println("2. Sort by model");
            System.out.println("3. Sort by color");
            System.out.println("4. Sort by mileage");
            choice1 = UserDataService.getInt("\nPlease enter menu number : ");
            if (choice1 < 1 || choice1 > 4) {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }
        } while (!Arrays.asList(1, 2, 3, 4).contains(choice1));

        int choice2;
        do {

            System.out.println("1. Sort ascending");
            System.out.println("2. Sort descending");
            choice2 = UserDataService.getInt("\nPlease enter menu number : ");
            if (choice2 < 1 || choice2 > 2) {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }
        } while (!Arrays.asList(1, 2).contains(choice2));

        boolean type;
        if (choice2 == 1) {
            type = false;
        } else {
            type = true;
        }
        SortType sortType = sortTypes[choice1 - 1];

        return carService.sortBy(sortType, type);
    }

    public void insertCar() {
        String model = "";
        Color color = null;
        double price = 0;
        int mileage = 0;
        Set<String> equipment = new HashSet<>();
        int option;
        do {
            System.out.println("\n<----Menu---->");
            System.out.println("1. Model Input");
            System.out.println("2. Color Menu");
            System.out.println("3. Price Input");
            System.out.println("4. Mileage Input");
            System.out.println("5. Add Components");
            System.out.println("6. Show My Car");
            System.out.println("7. Save Car");
            System.out.println("8. Back To Main Menu");

            option = UserDataService.getInt("\nPlease enter menu number : ");

            if (option < 1 || option > 8) {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }
            switch (option) {
                case 1:
                    do {
                        model = UserDataService.getString("\nPlease input car model. Pattern : A-Z");
                    } while (!model.matches("[A-Z ]+"));
                    break;

                case 2:
                    int c;
                    Color[] colors = Color.values();
                    do {
                        System.out.println("\n<----Color Menu---->");
                        System.out.println("1. White");
                        System.out.println("2. Black");
                        System.out.println("3. Silver");
                        System.out.println("4. Blue");
                        c = UserDataService.getInt("\nPlease enter menu number : ");
                        if (c < 1 || c > 4) {
                            System.err.println("PLEASE INSERT NUMBER FROM MENU");
                        }
                    } while (!Arrays.asList(1, 2, 3, 4).contains(c));
                    color = colors[c - 1];
                    break;

                case 3:
                    do {
                        price = UserDataService.getDouble("\nPlease input car price");
                    } while (price < 0);
                    break;

                case 4:
                    do {
                        mileage = UserDataService.getInt("\nPlease input car mileage. Mileage must be greater or equal 0");
                    } while (mileage <= 0);
                    break;

                case 5:

                    System.out.println("\nPlease input car components, if you're done please insert '0'. Equipment pattern : 'A-Z'");
                    String eq;
                    do {
                        eq = UserDataService.getString("Insert component : ");
                        if (!eq.matches("[A-Z]+") && !eq.equals("0")) {
                            System.err.println("\nTRY AGAIN ! COMPONENT PATTERN: 'A-Z'");
                        } else if (eq.matches("[A-Z]+")) {
                            equipment.add(eq);
                            System.out.println("If you're done please enter '0'");
                        }
                    } while (!eq.equals("0"));
                    break;

                case 6:
                    System.out.println("YOUR CAR : ");
                    System.out.println("Model : " + model);
                    System.out.println("Color : " + color);
                    System.out.println("Price : " + price);
                    System.out.println("Mileage : " + mileage);
                    System.out.println("Equipment : " + equipment);
                    break;

                case 7:
                    carService.insertCar(Car.builder()
                            .mileage(mileage)
                            .price(new BigDecimal(price))
                            .model(model)
                            .equipment(equipment)
                            .color(color)
                            .build());
                    return;

                case 8:
                    return;
            }
        } while (option != 7);


    }

    public List<Car> getCarsFromPriceRange() {
        int min;
        int max;
        do {
            min = UserDataService.getInt("\nPlease enter min price : ");
            max = UserDataService.getInt("\nPlease enter max price : ");
            if (min >= max) {
                System.err.println("MIN VALUE SHOULD BE GREATER THAN MAX VALUE");
            }
        } while (min >= max || min <= 0);
        List<Car> carsFromPriceRange = carService.carsWithPriceInRange(new BigDecimal(min), new BigDecimal(max));
        if (carsFromPriceRange.size() == 0) {
            System.err.println("THERE IS NO CAR FROM THIS PRICE RANGE");
            return null;
        } else {
            return carsFromPriceRange;
        }
    }
}
