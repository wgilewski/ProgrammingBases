package com.app.model;

import com.app.service.AbstractBankingOperations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreditCard extends AbstractBankingOperations {
    private String pinCode;
    final static Scanner sc = new Scanner(System.in);

    @Override
    public void payment(double amount) {
        System.out.println("INSERT YOUR PIN : ");
        Scanner sc = new Scanner(System.in);
        String pin = sc.next();
        if (checkPinCode(pin) == true) {
            if (getAccountBalance() - amount < 0) {
                getOperations().add("CANCELED");
                System.out.println("ERROR");
            } else {
                setAccountBalance(getAccountBalance() - amount);
                getOperations().add("PAYMENT_" + amount);
            }
        } else {
            getOperations().add("LOGIN_ERROR");
        }

    }

    private boolean checkPinCode(String pin) {
        if (pin.equals(pinCode)) {
            return true;
        } else
            return false;
    }

    @Override
    public void cleanTransactionHistory() {

        System.out.println("INSERT YOUR PIN : ");
        String pin = sc.next();
        if (pin.equals(pinCode)) {
            super.cleanTransactionHistory();
        } else {
            getOperations().add("LOGIN_ERROR");
        }

    }

    public static CreditCard bonus(List<CreditCard> creditCards) {
        List<Integer> integers = new ArrayList<>();
        Map<Integer, CreditCard> map = new HashMap<>();
        for (CreditCard creditCard : creditCards) {
            int counter = 0;
            for (String s : creditCard.getOperations()) {
                if (s.equals("LOGIN_ERROR")) ;
                {
                    counter += 1;
                }
            }
            map.put(counter, creditCard);
            integers.add(counter);
        }
        int min = Collections.min(integers);
        System.out.println(map);
        return map.get(min);

    }

    public static CreditCard biggerAccountAmount(CreditCard creditCard1, CreditCard creditCard2) {
        if (creditCard1.getAccountBalance() == creditCard2.getAccountBalance()) {
            return null;
        }
        if (creditCard1.getAccountBalance() > creditCard2.getAccountBalance()) {
            return creditCard1;
        } else {
            return creditCard2;
        }
    }

    public static void cardOperations(List<CreditCard> creditCards) {

        for (CreditCard creditCard : creditCards) {
            int payment = 0;
            int deposit = 0;
            int canceled = 0;
            int error = 0;
            for (String s : creditCard.getOperations()) {
                String[] operations = s.split("_");
                String operation = operations[0];

                if (operation.equals("PAYMENT")) {
                    payment += 1;
                } else if (operation.equals("DEPOSIT")) {
                    deposit += 1;
                } else if (operation.equals("CANCELED")) {
                    canceled += 1;
                } else if (operation.equals("LOGIN")) {
                    error += 1;
                }
            }
            System.out.println(creditCard.getPinCode());
            System.out.println("PAYMENTS : " + payment);
            System.out.println("DEPOSITS : " + deposit);
            System.out.println("CANCELED : " + canceled);
            System.out.println("LOGIN_ERRORS : " + error);
            System.out.println();

        }
    }


}
