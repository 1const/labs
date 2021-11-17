package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.DegreeFunction;

public class DegreeFunctionTest {
    @Test
    public void test(){
        DegreeFunction degree = new DegreeFunction();
        System.out.println(degree.apply(5));
    }
}