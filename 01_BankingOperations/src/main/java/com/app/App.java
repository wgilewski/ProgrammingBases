package com.app;

import com.app.model.CreditCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {

        CreditCard creditCard1 = new CreditCard("1111");
        CreditCard creditCard2 = new CreditCard("2222");

        List<CreditCard> creditCards = new ArrayList<>(Arrays.asList(creditCard1,creditCard2));

        System.out.println(CreditCard.biggerAccountAmount(creditCard1,creditCard2));

        creditCard1.deposit(50);
        creditCard2.deposit(50);


        creditCard1.payment(100);
        creditCard2.payment(100);


        CreditCard.cardOperations(creditCards);


    }



}
