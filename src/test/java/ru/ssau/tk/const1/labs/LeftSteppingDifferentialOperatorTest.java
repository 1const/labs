package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.MathFunction;
import ru.ssau.tk.const1.labs.functions.SqrFunction;
import ru.ssau.tk.const1.labs.operations.LeftSteppingDifferentialOperator;

public class LeftSteppingDifferentialOperatorTest {
    @Test
    public void defaultMethodsTest(){
        LeftSteppingDifferentialOperator operator = new LeftSteppingDifferentialOperator(3.0);
        MathFunction function =  new SqrFunction();
        System.out.println(operator.derive(function).apply(6));
        System.out.println(operator.getStep());
        operator.setStep(2);
        System.out.println(operator.derive(function).apply(6));
    }
}
