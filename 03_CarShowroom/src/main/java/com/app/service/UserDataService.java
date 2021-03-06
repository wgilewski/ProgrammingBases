package com.app.service;

import com.app.exceptions.MyException;

import java.util.Scanner;

public class UserDataService {

    private static final Scanner sc = new Scanner(System.in);

    static int getInt(String message) {
        System.out.println(message);
        String text = sc.nextLine();
        if (text == null || !text.matches("\\d+")) {
            throw new MyException("INT VALUE IS NOT CORRECT: " + text);
        }
        return Integer.parseInt(text);
    }

    static double getDouble(String message) {
        System.out.println(message);
        String text = sc.nextLine();
        if (text == null || !text.matches("[0-9]+[.[0-9]+]?")) {
            throw new MyException("DOUBLE VALUE IS NOT CORRECT: " + text);
        }
        return Double.valueOf(text);
    }

    static String getString(String message) {
        System.out.println(message);
        return sc.nextLine();
    }


    public static void close() {
        sc.close();
    }
}
