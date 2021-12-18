package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.MathFunction;
import ru.ssau.tk.const1.labs.functions.SqrFunction;
import ru.ssau.tk.const1.labs.operations.LeftSteppingDifferentialOperator;

import static org.junit.Assert.assertEquals;

public class LeftSteppingDifferentialOperatorTest {
    @Test
    public void defaultMethodsTest() {
        double delta = 0.001;
        LeftSteppingDifferentialOperator operator = new LeftSteppingDifferentialOperator(3.0);
        MathFunction function = new SqrFunction();
        assertEquals(0.2391, operator.derive(function).apply(6), delta);
        assertEquals(3, operator.getStep(), delta);
        operator.setStep(2);
        assertEquals(0.2247, operator.derive(function).apply(6), delta);
    }
}
