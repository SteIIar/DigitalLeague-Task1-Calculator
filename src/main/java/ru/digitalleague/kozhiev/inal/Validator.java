package ru.digitalleague.kozhiev.inal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


    private static List<Pattern> patterns = new ArrayList<>();

    static {

        patterns.add(Pattern.compile("\\d+[ ]+\\d+"));      //пробелы
        patterns.add(Pattern.compile("[^\\d+-/*/]"));    //буквы
        patterns.add(Pattern.compile("^[/*]|^[+\\-]{2,}|^\\+-|^-\\+|[+\\-*/]{3,}|\\+\\+|--|[/*]{2,}|[+\\-][/*]|[+\\-/*]+$")); //знаки
    }


    public static boolean isValid(String expression) {
        if (expression == null || expression.isEmpty())
            return false;

        Matcher matcher;

        matcher = patterns.get(0).matcher(expression);
        if (matcher.find())
            return false;

        expression = expression.replace(" ", "");

        matcher = patterns.get(1).matcher(expression);
        if (matcher.find()) {
            return false;
        }

        matcher = patterns.get(2).matcher(expression);
        if (matcher.find())
            return false;

        return true;
    }
}
