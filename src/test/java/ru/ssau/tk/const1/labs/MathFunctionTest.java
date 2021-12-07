package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.*;

import static org.junit.Assert.assertEquals;

public class MathFunctionTest {

    @Test
    public void testAndThen() {
        double[] arrOfX1 = {1, 2, 3};
        double[] arrOfY1 = {-3, -6, -9};
        double[] arrOfX2 = {1, 10, 20};
        double[] arrOfY2 = {0.5, 5, 10};
        MathFunction minusThreeFunc = new ArrayTabulatedFunction(arrOfX1, arrOfY1);
        MathFunction halfFunc = new LinkedListTabulatedFunction(arrOfX2, arrOfY2);
        MathFunction sqrFunc = new SqrFunction();
        double delta = 0.001;
        MathFunction composite1 = sqrFunc.andThen(halfFunc);
        assertEquals(1.118 , composite1.apply(2.5), delta);
        MathFunction composite2 = minusThreeFunc.andThen(composite1);
        assertEquals(-3.3541 , composite2.apply(2.5), delta);
    }
}