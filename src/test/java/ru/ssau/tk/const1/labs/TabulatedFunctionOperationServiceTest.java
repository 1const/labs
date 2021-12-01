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
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);
        service.getFactory();
        service.setFactory(new ArrayTabulatedFunctionFactory());
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(xValues, yValues);
        MathFunction function = new SqrFunction();
        LinkedListTabulatedFunction list2 = new LinkedListTabulatedFunction(function, 1., 9., 5);
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.addition(arr, list2));
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function, 1., 5., 5);
        System.out.println("ADDITION");
        System.out.println(service.addition(arr, list).toString());
        System.out.println("DIVIDE");
        System.out.println(service.divide(arr, list).toString());
        System.out.println("SUBTRACTION");
        System.out.println(service.subtraction(arr, list).toString());
        System.out.println("MULTIPLICATION");
        System.out.println(service.multiplication(arr, list).toString());
    }

}
