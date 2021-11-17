package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.ConstantFunction;

import static org.junit.Assert.*;

public class ConstantFunctionTest {
    @Test
    public void test(){
        double delta = 0.001;
        ConstantFunction constantFunction = new ConstantFunction(5.);
       assertEquals(5., constantFunction.apply(2.), delta);
       assertEquals(5., constantFunction.getConstant(), delta);
    }
}