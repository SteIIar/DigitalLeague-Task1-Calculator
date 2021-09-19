package ru.digitalleague.kozhiev.inal;

import ru.digitalleague.kozhiev.inal.exception.ExpressionFormatException;

import java.io.IOException;

public interface Computable {

    double compute(String expression) throws ExpressionFormatException;
}
