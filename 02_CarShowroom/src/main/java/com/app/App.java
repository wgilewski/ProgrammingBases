package com.app;

import com.app.service.GenerateData;
import com.app.service.MenuService;

public class App 
{
    public static void main( String[] args )
    {
        //Generate sample json file with cars;
        //GenerateData.generate();

        new MenuService().mainMenu();
    }
}
