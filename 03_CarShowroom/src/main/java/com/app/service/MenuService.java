package com.app.service;

import com.app.exceptions.MyException;
import com.app.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.List;

@AllArgsConstructor
@Data

public class MenuService {

    private final UserDataService userDataService = new UserDataService();
    private final CarsService carsService = new CarsService();

    public void mainMenu()
    {

        while (true)
        {
            try
            {
                 System.out.println("\n<----Menu---->");
                 System.out.println("1. Sort cars by");
                 System.out.println("2. Find car from price range and body color");
                 System.out.println("3. Sorted cars with engine type ");
                 System.out.println("4. Car statistics");
                 System.out.println("5. Cars mileage map");
                 System.out.println("6. Cars with tyre type");
                 System.out.println("7. Cars with equipment");
                 System.out.println("8. Add new car");
                 System.out.println("9. Back to original car list");
                 System.out.println("10. Show all cars");
                 System.out.println("11. Close\n");
                 int a = UserDataService.getInt("Please enter menu number : ");

                 if (a < 1 || a > 11)
                 {
                     System.err.println("PLEASE INSERT NUMBER FROM MENU");
                 }


                 switch (a)
                 {
                     case 1:
                         List<Car> sortedCars = sortedCars();
                         sortedCars.stream().forEach(System.out::println);
                         break;


                     case 2:
                         List<Car> priceRangeAndBodyColor = priceRangeAndBodyColor();
                         priceRangeAndBodyColor.stream().forEach(System.out::println);
                         break;


                     case 3:
                         List<Car> sortedCarsWithEngineType = sortedCarsWithEngineType();
                         sortedCarsWithEngineType.stream().forEach(System.out::println);
                         break;


                     case 4:
                         statistics();
                         break;


                     case 5:
                         Map<Car,Integer> carIntegerMap = carsService.carMileageMap();
                         carIntegerMap.entrySet().forEach(System.out::println);
                         break;


                     case 6:
                         Map<TyreType, List<Car>> tyreTypeListMap = carsService.carsWithTyreType();
                         tyreTypeListMap.entrySet().stream().forEach(System.out::println);
                         break;


                     case 7:
                         List<Car> carsWithComponents = carsWithComponents();
                         carsWithComponents.stream().forEach(System.out::println);
                         break;


                     case 8:
                         newCar();
                         break;


                     case 9:
                         carsService.backToOriginalCarList();
                         break;


                     case 10:
                         carsService.showAllCars();
                         break;


                     case 11:
                         UserDataService.close();
                         return;
                 }

            }catch (MyException e)
            {
                System.out.println(e.getExceptionMessage());
            }
        }
    }

    public List<Car> carsWithComponents()
    {
        List<String> equipment = new ArrayList<>();
        System.out.println("\nPlease input car components, if you're done please enter '0'. Equipment pattern : 'A-Z'");
        String eq;
        do {
            eq = UserDataService.getString("Input component : ");
            if (!eq.matches("[A-Z]+") && !eq.equals("0")) {
                System.out.println("\nTry again ! Component pattern : 'A-Z'");
            } else if (eq.matches("[A-Z]+")) {
                equipment.add(eq);
                System.out.println("\nIf you're done please enter '0'");
            }
        } while (!eq.equals("0"));

        List<Car> carsWithEquipment = carsService.carsWithComponents(equipment);

        if (carsWithEquipment.size() == 0) {
            System.out.println("There is no cars with those components");
            return null;
        } else
            return carsWithEquipment;
    }
    public void statistics()
    {
        int menu4;

        do {
            System.out.println("\n<----Statistics Menu---->");
            System.out.println("1. Price statistics");
            System.out.println("2. Engine power statistics");
            System.out.println("3. Mileage statistics");
            menu4 = UserDataService.getInt("\nPlease enter menu number : ");
            if (menu4 < 1 || menu4 > 3)
            {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }
        }while (!Arrays.asList(1,2,3).contains(menu4));

        carsService.statistics(menu4);
    }
    public List<Car> sortedCarsWithEngineType()
    {
        int engineType;
        do {
            System.out.println("\n<----Engine Menu---->");
            System.out.println("1. Diesel");
            System.out.println("2. Gasoline");
            System.out.println("3. LPG\n");
            engineType = UserDataService.getInt("Please enter menu number : ");
            if (engineType < 1 || engineType > 3)
            {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }
        } while (!Arrays.asList(1, 2, 3).contains(engineType));

        EngineType[] engineTypes = EngineType.values();
        return carsService.sortedCarsWithEngineTypeAsArgument(engineTypes[engineType - 1]);
    }
    public List<Car> priceRangeAndBodyColor()
    {
        CarBodyColor[] carBodyColors = CarBodyColor.values();
        int color;
        do {
            System.out.println("\n<----Color Menu--->");
            System.out.println("1. Black");
            System.out.println("2. Silver");
            System.out.println("3. White");
            System.out.println("4. Red");
            System.out.println("5. Blue");
            System.out.println("6. Green\n");
            color = UserDataService.getInt("Please enter menu number : ");
            if (color < 1 || color > 6)
            {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }
        } while (!Arrays.asList(1, 2, 3, 4, 5, 6).contains(color));
        int min;
        int max;
        do {
            min = UserDataService.getInt("\nPlease enter min price : " );
            max = UserDataService.getInt("\nPlease enter max price : " );
            if (min >= max)
            {
                System.err.println("MIN VALUE SHOULD BE GREATER THAN MAX VALUE");
            }
        } while (min >= max || min <= 0);
        return carsService.findByCarBodyAndPriceRange(carBodyColors[color - 1], min, max);
    }
    public List<Car> sortedCars()
    {
        int menu1;
        do {
            System.out.println("\n<----Sort Menu---->");
            System.out.println("1. Sort by components quantity");
            System.out.println("2. Sort by wheel size");
            System.out.println("3. Sort by engine power\n");
            menu1 = UserDataService.getInt("\nPlease enter menu number : ");
            if (menu1 < 1 || menu1 > 3)
            {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }
        }while (!Arrays.asList(1,2,3).contains(menu1));

        int menu2;
        do {
            System.out.println("1. Sort ascending");
            System.out.println("2. Sort descending\n");
            menu2 = UserDataService.getInt("\nPlease enter menu number : ");

            if (menu2 < 1 || menu2 > 2)
            {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }
        }while (!Arrays.asList(1,2).contains(menu2));

        if (menu2 == 1)
        {
            return carsService.sortCars(menu1,false);
        }
        else return carsService.sortCars(menu1,true);
    }
    public void newCar()
    {
        String model = null;
        CarBodyColor color = null;
        double price = 0;
        int mileage = 0;
        Engine engine = null;
        CarBody carBody = null;
        Wheel wheel = null;
        int option;

        do {
            System.out.println("\n<----Menu---->");
            System.out.println("1. Model Input");
            System.out.println("2. Price Input");
            System.out.println("3. Mileage Input");
            System.out.println("4. Engine Menu");
            System.out.println("5. Car Body Menu");
            System.out.println("6. Wheel menu");
            System.out.println("7. Show my car");
            System.out.println("8. You're done");

            option = UserDataService.getInt("\nPlease enter menu number : ");
            switch (option) {
                case 1:
                    do {
                        System.out.println();
                        model = UserDataService.getString("\nPlease input car model. Pattern : A-Z");
                    } while (!model.matches("[A-Z ]+"));
                    break;
                case 2:
                    do {
                        System.out.println();
                        price = UserDataService.getDouble("\nPlease input car price. Price must be bigger than 0");
                    } while (price < 0);
                    break;

                case 3:
                    do {
                        System.out.println();
                        mileage = UserDataService.getInt("\nPlease input car mileage. Mileage must be bigger than 0");
                    } while (mileage < 0);
                    break;

                case 4:
                    engine = newEngine();
                    break;
                case 5:
                    carBody = newCarBody();
                    break;
                case 6:
                    wheel = newWheel();
                    break;
                case 7:
                    System.out.println("My Car : ");
                    System.out.println("Model : " + model + "\nPrice : " + price + "\nMileage : " + mileage) ;
                    System.out.println(carBody);
                    System.out.println(wheel);
                    System.out.println(engine);
                    break;
                case 8:
                    break;
            }
        }while (option != 8);

        if (model == null || price == 0 || mileage == 0 || engine == null || carBody == null || wheel == null)
        {
            System.out.println("Adding car failed !");
        }
        else
        {
            carsService.insertCar(Car.builder()
                    .model(model)
                    .price(new BigDecimal(price))
                    .mileage(mileage)
                    .wheel(wheel)
                    .engine(engine)
                    .carBody(carBody)
                    .build());
        }
    }
    public Engine newEngine()
    {
        EngineType engineType = null;
        double power = 0;
        int a;
        do
        {
            System.out.println("\n<----Engine Menu---->");
            System.out.println("1. Engine Type");
            System.out.println("2. Engine Power");
            System.out.println("3. Back Car Menu");
            a = UserDataService.getInt("\nPlease enter menu number : ");

            if (a < 1 || a > 3)
            {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }

            switch (a)
            {
                case 1:

                    EngineType[] engineTypes = EngineType.values();
                    int engine;
                    do {
                        System.out.println("\n<----Engine Type---->");
                        System.out.println("1. Diesel");
                        System.out.println("2. Gasoline");
                        System.out.println("3. Lpg");
                        engine = UserDataService.getInt("\nPlease enter menu number : ");

                        if (engine > 1 || engine < 3)
                        {
                            System.err.println("PLEASE INSERT NUMBER FROM MENU");
                        }

                    }while (!Arrays.asList(1,2,3).contains(engine));
                    engineType = engineTypes[engine-1];
                    break;
                case 2:

                    double enginePower;
                    do {
                        enginePower = UserDataService.getDouble("\nPlease input engine power : ");
                    } while (enginePower < 0);
                    power = enginePower;
                    break;

                case 3:
                    break;
            }
        }while (a != 3);

        if (engineType == null || power == 0)
        {
            System.out.println("Adding engine failed!");
            return null;
        }
        else
            return new Engine().builder()
                    .type(engineType)
                    .power(power)
                    .build();
    }
    public CarBody newCarBody()
    {
        CarBodyColor carBodyColor = null;
        CarBodyType carBodyType = null;
        Set<String> components = new HashSet<>();

        int a;
        do {
            System.out.println("\n<----Car Body Menu---->");
            System.out.println("1. Color Menu");
            System.out.println("2. Car Body Type Menu");
            System.out.println("3. Add Components");
            System.out.println("4. Back Car Menu");
            a = UserDataService.getInt("\nPlease enter menu number : ");
            if (a < 1 || a > 4)
            {
                System.err.println("PLEASE INSERT NUMBER FROM MENU");
            }
            switch (a)
            {
                case 1:
                    CarBodyColor[] carBodyColors = CarBodyColor.values();
                    int color;
                    do {
                        System.out.println("\n<----Color Menu--->");
                        System.out.println("1. Black");
                        System.out.println("2. Silver");
                        System.out.println("3. White");
                        System.out.println("4. Red");
                        System.out.println("5. Blue");
                        System.out.println("6. Green\n");
                        color = UserDataService.getInt("\nPlease enter menu number : ");
                    }while (!Arrays.asList(1,2,3,4,5,6).contains(color));

                    carBodyColor = carBodyColors[color-1];
                    break;

                case 2:
                    CarBodyType[] carBodyTypes = CarBodyType.values();
                    int body;
                    do {
                        System.out.println("\n<----Car Body Type Menu--->");
                        System.out.println("1. Sedan");
                        System.out.println("2. HatchBack");
                        System.out.println("3. Combi");
                        body = UserDataService.getInt("Please enter menu number : ");
                        if (body < 1 || body > 3)
                        {
                            System.err.println("PLEASE INSERT NUMBER FROM MENU");
                        }
                    }while (!Arrays.asList(1,2,3).contains(body));
                    carBodyType = carBodyTypes[body-1];
                    break;

                case 3:
                    components.clear();
                    System.out.println("\nPlease input car components, if you're done please enter '0'. Equipment pattern : 'A-Z'");
                    String eq;
                    do {
                        eq = UserDataService.getString("Input component : ");
                        if (!eq.matches("[A-Z]+") && !eq.equals("0"))
                        {
                            System.out.println("\nTry again ! Component pattern : 'A-Z'");
                        } else if (eq.matches("[A-Z]+"))
                        {
                            components.add(eq);
                            System.out.println("If you're done please enter '0'");
                        }
                    } while (!eq.equals("0"));
                    break;
                case 4:
                    break;
            }

        }while (a != 4);

        if (carBodyColor == null || carBodyType == null || components.isEmpty())
        {
            System.out.println("Adding car body failed !");
            return null;
        }
        else
            return CarBody.builder()
                    .color(carBodyColor)
                    .type(carBodyType)
                    .components(components)
                    .build();
    }
    public Wheel newWheel()
    {
        TyreType type = null;
        String model = "";
        int size = 0;
        int a;

        do {
            System.out.println("\n<----Wheel Menu---->");
            System.out.println("1. Model Input");
            System.out.println("2. Size Input");
            System.out.println("3. Tyre Type Menu");
            System.out.println("4. Back Car Menu");
            a = UserDataService.getInt("\nPlease enter menu number : ");
            switch (a)
            {
                case 1:
                    do {
                        model = UserDataService.getString("\nPlease input wheel model. Pattern : A-Z");
                    } while (!model.matches("[A-Z ]+"));
                    break;
                case 2:
                    do {
                        size = UserDataService.getInt("\nPlease input wheel size");
                    } while (size < 0);
                    break;
                case 3:

                    int tyreType;
                    do {
                        System.out.println("\n<----Tyre Type Menu---->");
                        System.out.println("1. Winter");
                        System.out.println("2. Summer");
                        tyreType = UserDataService.getInt("\nPlease enter menu number : ");
                        if (tyreType < 1 || tyreType > 2)
                        {
                            System.err.println("PLEASE INSERT NUMBER FROM MENU");
                        }
                    }while (!Arrays.asList(1,2).contains(tyreType));

                    TyreType[] tyreTypes = TyreType.values();
                    type = tyreTypes[tyreType - 1];

                    break;
                case 4:
            }
        }while (a != 4);

        if (model == null || type == null)
        {
            System.out.println("Adding wheel failed !");
            return null;
        }
        else
            return Wheel.builder()
                    .type(type)
                    .model(model)
                    .size(size)
                    .build();
    }



}
