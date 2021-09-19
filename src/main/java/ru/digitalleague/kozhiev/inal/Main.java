package ru.digitalleague.kozhiev.inal;

import ru.digitalleague.kozhiev.inal.exception.ExpressionFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Calculator calculator = new Calculator();

        System.out.println("Enter expression: ");

        String expression = reader.readLine();

        try {
            double result = calculator.compute(expression);

            System.out.println("Answer of expression " + expression + " = " + result);
        } catch (ExpressionFormatException e) {
            e.printStackTrace();
        }


    }
}
