package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.operations.TabulatedDifferentialOperator;

public class TabulatedDifferentialOperatorTest {
    @Test
    public void defaultMethodsTest(){
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        TabulatedFunctionFactory factory = operator.getFactory();
        operator.setFactory(factory);
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        TabulatedFunction function =  new LinkedListTabulatedFunction(xValues,yValues);
        System.out.println(operator.derive(function).toString());
    }
}
