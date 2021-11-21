package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.CompositeFunction;
import ru.ssau.tk.const1.labs.functions.MathFunction;
import ru.ssau.tk.const1.labs.functions.SqrFunction;

import static org.junit.Assert.assertEquals;

public class MathFunctionTest {

    @Test
    public void testAndThen() {
        SqrFunction sqr_func1 = new SqrFunction();
        SqrFunction sqr_func2 = new SqrFunction();
        SqrFunction sqr_func3 = new SqrFunction();
        MathFunction composite1 = sqr_func3.andThen(sqr_func2).andThen(sqr_func1);
        CompositeFunction composite2 = new CompositeFunction(sqr_func1, sqr_func3.andThen(sqr_func2));
        assertEquals(5.0, sqr_func1.andThen(sqr_func2).apply(625), 0);
    }
}