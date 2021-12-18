package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.DegreeFunction;

import static org.junit.Assert.assertEquals;

public class DegreeFunctionTest {
    @Test
    public void test(){
        DegreeFunction degree = new DegreeFunction();
        assertEquals(2.92401, degree.apply(5), 0.001);
    }
}