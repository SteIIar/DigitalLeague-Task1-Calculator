package ru.digitalleague.kozhiev.inal;

import ru.digitalleague.kozhiev.inal.exception.ExpressionFormatException;

import java.util.LinkedList;
import java.util.List;

public class Calculator implements Computable {

    private static final char PLUS     = '+';
    private static final char MINUS    = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDER  = '/';

    private static final char NEGATIVE = '-';

    private List<Double> values = new LinkedList<>();
    private List<Character> operations = new LinkedList<>();

    @Override
    public double compute(String expression) throws ExpressionFormatException {

        if (expression == null || expression.isEmpty())
            throw new ExpressionFormatException();

        if (!Validator.isValid(expression))
            throw new ExpressionFormatException();

        String expr = expression.replace(" ", "");

        fillLists(expr);
        calculateAndReplacePriorityOperations();

        double result = calculate();
        clear();
        return result;
    }

    public void fillLists(String expr) throws ExpressionFormatException {

        int i = 0;
        while (i < expr.length()) {

            char c = expr.charAt(i);

            /*
            maybe it's just a negative symbol of digit
             */
            if (isOperation(c)) {
                if (i + 1 >= expr.length())
                    throw new ExpressionFormatException("Expected number after operation");
                if ((c == NEGATIVE || c == PLUS) && (i == 0 || isOperation(expr.charAt(i-1)))) {
                    if (isOperation(expr.charAt(i+1)))
                        throw new ExpressionFormatException("Operation after " + c);

                    String number = readFirstNumberFromExpression(expr.substring(++i));  // +1 skip negative char
                    i += number.length();
                    double value = c == PLUS ? Double.parseDouble(number) : -Double.parseDouble(number);
                    values.add(value); //add negative
                } else {
                    operations.add(c);
                    i++;
                }
            }

            else if (Character.isDigit(c)) {
                String number = readFirstNumberFromExpression(expr.substring(i));
                i += number.length();
                double value = Double.parseDouble(number);
                values.add(value);
            }

        }

        if (values.size() <= operations.size())
            throw new ExpressionFormatException();

    }

    private double calculate() {


        while (!values.isEmpty() && !operations.isEmpty()) {
            double value = operations.get(0) == PLUS ? values.get(0) + values.get(1) : values.get(0) - values.get(1);
            values.remove(0);
            operations.remove(0);
            values.set(0, value);
        }
        double result = values.get(0);
        values.remove(0);
        clear();
        return result;
    }

    private void calculateAndReplacePriorityOperations() {
        for(int i = 0; i < operations.size(); ) {

            char c = operations.get(i);

            if (c == '*' || c == '/') {
                double result = c == '*' ? values.get(i) * values.get(i+1) : values.get(i) / values.get(i+1);

                values.remove(i);
                values.set(i, result);

                operations.remove(i);

            } else {
                i++;
            }
        }
    }

    private static boolean isOperation(char ch) {
        switch (ch) {
            case PLUS:
            case MINUS:
            case DIVIDER:
            case MULTIPLY: return true;
        }
        return false;
    }


    private String readFirstNumberFromExpression(String expr) {
        StringBuilder number = new StringBuilder();

        int i = 0;
        char c;

        while (i < expr.length()
                && Character.isDigit(c = expr.charAt(i++)))
        {
            number.append(c);
        }

        return number.toString();
    }


    private void clear() {
        values.clear();
        operations.clear();
    }
}
