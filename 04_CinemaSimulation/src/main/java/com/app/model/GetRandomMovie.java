package com.app.model;


import java.util.Random;

public class GetRandomMovie implements IMovie {
    final static String[] TITLES = {"KOTEK", "BLADA", "MAREK", "sodkao"};
    final static String[] TYPES = {"horror", "sensacyjny", "wojenny", "komedia"};
    final static String[] DIRECTORS = {"ANDRZEJ nowak", "MAREK kot", "kot MAREK", "PIES kot"};


    @Override
    public Movie getMovie() {
        Random rnd = new Random();
        int randomInt = rnd.nextInt(3);
        return Movie
                .builder()
                .title(TITLES[randomInt])
                .type(TYPES[randomInt])
                .directorName(DIRECTORS[randomInt])
                .build();
    }

}
