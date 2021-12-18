package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.IdentityFunction;
import ru.ssau.tk.const1.labs.functions.MathFunction;

import static org.junit.Assert.assertEquals;

public class IdentityFunctionTest {
    @Test
    public void test() {
        MathFunction identity = new IdentityFunction();
        assertEquals(5, identity.apply(5), 0.0001);
    }
}