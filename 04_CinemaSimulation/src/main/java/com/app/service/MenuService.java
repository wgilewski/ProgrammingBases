package com.app.service;

import com.app.Exceptions.CustomException;
import com.app.Exceptions.MyException;
import com.app.converters.MoviesJsonConverter;
import com.app.model.Movie;
import com.app.model.Seance;


import java.math.BigDecimal;
import java.util.*;

public class MenuService
{
    public void mainMenu()
    {

        Map<Integer,Movie> moviesMap = fromJsonToMovieMap();




            Movie movie = null;
            double price = 0;
            int duration = 0;
            int roomNumber = 0;
            try {


                while (true) {

                    System.out.println("<----SEANCE MENU---->");
                    System.out.println("1. Movie menu");
                    System.out.println("2. Price");
                    System.out.println("3. Duration");
                    System.out.println("4. Room number");
                    System.out.println("5. Show seance");
                    System.out.println("6. Save seance");


                    int option = UserDataService.getInt("Please insert menu number : ");

                    switch (option) {
                        case 1:
                            int movieNumber;
                            do {
                                moviesMap.entrySet().stream().forEach(System.out::println);

                                movieNumber = UserDataService.getInt("Please insert menu number : ");
                            } while (!moviesMap.containsKey(movieNumber));
                            movie = moviesMap.get(movieNumber);
                            System.out.println(movie);
                            break;


                        case 2:
                            do {
                                price = UserDataService.getDouble("Please insert price : ");
                            } while (price < 1);
                            break;


                        case 3:
                            do {
                                duration = UserDataService.getInt("Please insert duration : ");
                            } while (Arrays.asList(151, 96, 134).contains(duration));

                            break;


                        case 4:
                            do {
                                roomNumber = UserDataService.getInt("Please insert room number : ");
                            } while (roomNumber < 1 || roomNumber > 15);
                            break;


                        case 5:

                            System.out.println("Movie : " + movie);
                            System.out.println("Price : " + price);
                            System.out.println("Duration : " + duration);
                            System.out.println("Room number : " + roomNumber);

                            break;

                        case 6:

                            System.out.println("sssssss");
                            try {

                                Seance seance = new Seance(movie,new BigDecimal(price),duration,roomNumber);

                                System.out.println(seance);
                            }catch (CustomException e)
                            {
                                System.out.println(e.getExceptionMessage());
                            }


                            break;
                    }
                }
            }catch (MyException e)
            {
                System.out.println(e.getExceptionMessage());
            }





    }
    public Map<Integer,Movie> fromJsonToMovieMap()
    {

        Map<Integer,Movie> movieMap = new HashMap<>();
        MoviesJsonConverter moviesJsonConverter = new MoviesJsonConverter("movies.json");
        int i = 1;


        for (Movie m : moviesJsonConverter.fromJson().get().getMovies())
        {
            try
            {
                Movie a = new Movie(m.getType(),m.getTitle(),m.getDirectorName());
                movieMap.put(i++,a);
            }
            catch (CustomException c)
            {
                System.err.println(c.getExceptionMessage());
            }
        }
        return movieMap;
    }
 }

