package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.ConstantFunction;
import ru.ssau.tk.const1.labs.functions.ZeroFunction;

public class ZeroFunctionTest {
    @Test
    public void test(){
        ConstantFunction unitFunction = new ZeroFunction();
        System.out.println(unitFunction.apply(7));
    }
}