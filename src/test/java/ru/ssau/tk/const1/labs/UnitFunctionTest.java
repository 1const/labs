package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.ConstantFunction;
import ru.ssau.tk.const1.labs.functions.UnitFunction;

public class UnitFunctionTest {
    @Test
    public void test(){
        ConstantFunction unitFunction = new UnitFunction();
        System.out.println(unitFunction.apply(7));
    }
}