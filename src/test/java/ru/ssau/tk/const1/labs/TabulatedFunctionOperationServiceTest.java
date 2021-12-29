package ru.ssau.tk.const1.labs;

import org.junit.Assert;
import org.junit.Test;
import ru.ssau.tk.const1.labs.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.const1.labs.functions.*;
import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.operations.TabulatedFunctionOperationService;

public class TabulatedFunctionOperationServiceTest {
    @Test
    public void testDefaultFunctions() {
        double delta = 0.01;
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);
        service.getFactory();
        service.setFactory(new ArrayTabulatedFunctionFactory());
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        LinkedListTabulatedFunction arr = new LinkedListTabulatedFunction(xValues, yValues);
        MathFunction function = new SqrFunction();
        LinkedListTabulatedFunction list2 = new LinkedListTabulatedFunction(function, 1., 9., 5);
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.addition(arr, list2));
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function, 1., 5., 5);
        double[] addition = {2, 3.41, 4.73, 6, 7.23};
        double[] divide = {1, 1.41, 1.73, 2, 2.23};
        double[] subtraction = {0, 0.58, 1.26, 2, 2.76};
        double[] multiplication = {1, 2.82, 5.19, 8, 11.18};
        for (int i = 0; i < xValues.length; i++) {
            Assert.assertEquals(addition[i], service.addition(arr, list).getY(i), delta);
            Assert.assertEquals(divide[i], service.divide(arr, list).getY(i), delta);
            Assert.assertEquals(subtraction[i], service.subtraction(arr, list).getY(i), delta);
            Assert.assertEquals(multiplication[i], service.multiplication(arr, list).getY(i), delta);
        }
    }

}
