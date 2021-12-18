package ru.ssau.tk.const1.labs;

import org.junit.Assert;
import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.ConstantFunction;
import ru.ssau.tk.const1.labs.functions.UnitFunction;

public class UnitFunctionTest {
    @Test
    public void test(){
        ConstantFunction unitFunction = new UnitFunction();
        Assert.assertEquals(1, unitFunction.apply(7), 0.001);
    }
}