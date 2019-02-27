package com.app;

import com.app.service.GenerateData;
import com.app.service.MenuService;

public class App {
    public static void main(String[] args) {
        //GenerateData.generateData();
        new MenuService().mainMenu();
    }
}
