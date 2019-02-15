package com.app.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AbstractBankingOperations implements BankingOperations
{

    private List<String> operations = new ArrayList<>();
    private double accountBalance;

    @Override
    public void deposit(double amount)
    {
        if (amount > 0)
        {
            accountBalance += amount;
            operations.add("DEPOSIT_" + amount);
        }
        else
        {
            operations.add("CANCELED");
        }
    }
    @Override
    public void payment(double amount)
    {

    }
    @Override
    public void accountBalance()

    {
        System.out.println("YOUR ACCOUNT BALANCE : " + accountBalance);
    }
    public void checkOperationsAmountBefore(int checkOperations)
    {

        int listSize = operations.size();

        if ((listSize - checkOperations) < 0)
        {
            System.out.println("YOU HAVEN'T DONE SO MANY OPERATIONS");
        }
        else
        {
            int index = listSize - checkOperations;
            System.out.println(operations.get(index));
        }


    }
    public void canceledOperations()
    {

        int counter = 0;

        for (String s : operations)
        {
            if (s.equals("CANCELED"))
            {
                counter+=1;
            }
        }
        System.out.println("YOU HAVE " + counter + " CANCELED OPERATIONS");
    }
    public void cleanTransactionHistory()
    {
        operations.clear();
    }

}
