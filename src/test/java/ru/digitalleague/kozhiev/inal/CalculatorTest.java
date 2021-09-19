package ru.digitalleague.kozhiev.inal;

import org.junit.jupiter.api.Test;
import ru.digitalleague.kozhiev.inal.exception.ExpressionFormatException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private static final double DELTA = 0.0000001;

    private final Calculator calculator = new Calculator();

    @Test
    void shouldBeEquals() throws ExpressionFormatException {

        //1 number
        assertEquals(0,     calculator.compute("0")     ,   DELTA);
        assertEquals(-1,     calculator.compute("-1")     ,   DELTA);
        assertEquals(+1,     calculator.compute("+1")     ,   DELTA);

        //2 numbers
        assertEquals(0,     calculator.compute("4-4")   ,   DELTA);
        assertEquals(4+4,     calculator.compute("4+4")   ,   DELTA);
        assertEquals(4/4,     calculator.compute("4/4")   ,   DELTA);
        assertEquals(4*4,     calculator.compute("4*4")   ,   DELTA);

        //minus before number
        assertEquals(-4,     calculator.compute("-4")   ,   DELTA);
        assertEquals(-4-4,     calculator.compute("-4-4")   ,   DELTA);
        assertEquals(4*-4,     calculator.compute("4*-4")   ,   DELTA);
        assertEquals(-4*4,     calculator.compute("-4*4")   ,   DELTA);
        assertEquals(-4*-4,     calculator.compute("-4*-4")   ,   DELTA);
        assertEquals(-4+-4,     calculator.compute("-4+-4")   ,   DELTA);

        assertEquals(-4*15-4,     calculator.compute("-4*15-4")   ,   DELTA);
        assertEquals(-4*20-4,     calculator.compute("-4*20-4")   ,   DELTA);
        assertEquals(-4*21-4,     calculator.compute("-4*21-4")   ,   DELTA);
        assertEquals(-4*4-4,     calculator.compute("-4*4-4")   ,   DELTA);
        assertEquals(-4*-14+-4,     calculator.compute("-4*-14+-4")   ,   DELTA);


        assertEquals(-4*-14+-4,     calculator.compute("-4*  - 14 + -       4")   ,   DELTA);
        assertEquals(-4*-14+-4,     calculator.compute("  - 4       * -   14  +  -  4  ")   ,   DELTA);

        //brackets
//        assertEquals((10),    calculator.compute("(10)") ,   DELTA);
//        assertEquals((10 + 4),  calculator.compute("(10 + 4)")  ,   DELTA);
//        assertEquals((10 + 4)/2.0,  calculator.compute("(10 + 4)/2")  ,   DELTA);
//        assertEquals(((10 + 4)/2.0),  calculator.compute("((10 + 4)/2)")  ,   DELTA);
//        assertEquals(((10 + (4))/2.0),  calculator.compute("((10 + (4))/2)")  ,   DELTA);
//        assertEquals((  ( ( 10 + (4)))/   2.0),  calculator.compute("(  ( ( 10 + (4)))/   2)")  ,   DELTA);
//        assertEquals(((12345678))/((1 + 6/2.0+ 3) *(12 * 4 + 12)),     calculator.compute("((12345678))/((1 + 6/2 + 3) *(12 * 4 + 12))")     ,   DELTA);
    }


    @Test
    void shouldBeException() {
        assertThrows(ExpressionFormatException.class, () -> calculator.compute(null));

        assertThrows(ExpressionFormatException.class, () -> calculator.compute(""));

        assertThrows(ExpressionFormatException.class, () -> calculator.compute("+"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("-"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("*"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("/"));

        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1--1"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("--1"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("+-1"));

        assertThrows(ExpressionFormatException.class, () -> calculator.compute("--4/"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("-+3/1"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("/1"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1/"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1+"));


        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1 1"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1 + 1 -12/"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("-/"));

    }

/*
    @Test
    void shouldBeException() {
        assertThrows(ExpressionFormatException.class, () -> calculator.compute(null));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute(""));

        //no operator
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1 1"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute(" 9 1  + 1   "));

        //lack of number
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("+"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1+"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1+4-"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1+4-"));

        //extra operator
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1+/4"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1/+4"));

        //invalid char
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1+4a"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("b    1+4    "));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("b    123 v    "));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("123 + 4/5*12/1 + {6}"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("Two plus two is four Minus one, that's three, quick maths"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("123,4"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1234' + 1"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1234 + 1`"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("1234 + 1]"));

        //brackets
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("("));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute(")"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("()"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("(())"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute(")()()("));

        assertThrows(ExpressionFormatException.class, () -> calculator.compute("(((123 + 4)"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("(123 + 4)/4)"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("(123 + 4)(/4)"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("(123 + 4(/)4)"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute("(123 + 4(+)4)"));
        assertThrows(ExpressionFormatException.class, () -> calculator.compute(")123 + 44("));

    }
*/
}