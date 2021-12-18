package ru.ssau.tk.const1.labs;

import org.junit.Assert;
import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.operations.TabulatedDifferentialOperator;

public class TabulatedDifferentialOperatorTest {
    @Test
    public void defaultMethodsTest() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        TabulatedFunctionFactory factory = operator.getFactory();
        operator.setFactory(factory);
        double[] xValues = {1, 1.1, 1.2, 1.3, 1.4};
        double[] yValues = {1, 1.21, 1.44, 1.69, 1.96};
        TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        double[] yValuesTest = {2, 2.2, 2.4, 2.6, 2.8};
        for (int i = 0; i < xValues.length; i++) {
            Assert.assertEquals(yValuesTest[i], operator.derive(function).getY(i), 0.111);
        }
    }
}
