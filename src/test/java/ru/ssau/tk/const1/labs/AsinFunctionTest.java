package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.AsinFunction;
import ru.ssau.tk.const1.labs.functions.MathFunction;

import static org.junit.Assert.*;

public class AsinFunctionTest {
    @Test
    public void test(){
        MathFunction asin = new AsinFunction();
        assertEquals(Math.PI/6, asin.apply(0.5), 0.001);
    }
}