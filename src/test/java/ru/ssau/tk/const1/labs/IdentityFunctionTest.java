package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.IdentityFunction;
import ru.ssau.tk.const1.labs.functions.MathFunction;

public class IdentityFunctionTest {
    @Test
    public void test(){
        MathFunction identity = new IdentityFunction();
        System.out.println(identity.apply(5));
    }
}