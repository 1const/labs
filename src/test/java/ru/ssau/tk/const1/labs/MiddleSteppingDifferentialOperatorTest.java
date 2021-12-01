package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.MathFunction;
import ru.ssau.tk.const1.labs.functions.SqrFunction;
import ru.ssau.tk.const1.labs.operations.MiddleSteppingDifferentialOperator;

public class MiddleSteppingDifferentialOperatorTest {
    @Test
    public void defaultMethodsTest(){
        MiddleSteppingDifferentialOperator operator = new MiddleSteppingDifferentialOperator(3.0);
        MathFunction function =  new SqrFunction();
        System.out.println(operator.derive(function).apply(6));
    }
}
