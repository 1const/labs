package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;

import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;

import static org.junit.Assert.assertEquals;

public class FactoryTest {
    @Test
    public void testFactory() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        assertEquals(LinkedListTabulatedFunction.class, listFactory.createFromArray(xValues, yValues).getClass());
        assertEquals(LinkedListTabulatedFunction.class, arrayFactory.createFromArray(xValues, yValues).getClass());
    }
}