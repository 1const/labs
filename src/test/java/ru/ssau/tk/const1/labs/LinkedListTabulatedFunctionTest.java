package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.DegreeFunction;
import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.MathFunction;
import ru.ssau.tk.const1.labs.functions.SqrFunction;

import static org.junit.Assert.*;

public class LinkedListTabulatedFunctionTest {
    @Test
    public void testDefaultFunctionsConstructor1() {
        MathFunction function = new SqrFunction();
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function, 1., 9., 5);
        System.out.println(list.toString());
        System.out.println("tests: ");
        System.out.println(list.leftBound() + " - leftBound");
        System.out.println(list.rightBound() + " - rightBound");
        System.out.println(list.indexOfX(1) + " -index of non-existent x");
        System.out.println(list.indexOfX(3) + " -index of existent x");
    }

    @Test
    public void testDefaultFunctionsConstructor2() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        LinkedListTabulatedFunction arr2 = new LinkedListTabulatedFunction(xValues, yValues);
        System.out.println(arr2.toString());
        System.out.println(arr2.getCount() + " - length");
        arr2.setY(1, 4);
        System.out.println(arr2.getY(1) + "- new Y");
        System.out.println(arr2.leftBound() + " - leftBound");
        System.out.println(arr2.rightBound() + " - rightBound");
        System.out.println(arr2.indexOfX(1) + " indexOf 1");
    }

    @Test
    public void testApply() {
        MathFunction function = new SqrFunction();
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function, 1., 9., 5);
        System.out.println(list.toString());
        double delta = 0.1;                                              // скорее всего большая погрешность из-за маленького количества count
        assertEquals(1.0, list.apply(1), delta);             //левая граница
        assertEquals(3.0, list.apply(9), delta);             //правая граница
        assertEquals(2.0, list.apply(4), delta);            // не табличное значение внутри границы
        assertEquals(2.23, list.apply(5), delta);            // табличное значение внутри границы
    }

    @Test
    public void testInsert() {
        MathFunction function = new SqrFunction();
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function, 1., 9., 5);
        System.out.println(list.toString());
        list.insert(2.0, 2.0);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
    }

    @Test
    public void testApply2() {
        MathFunction function = new SqrFunction();
        MathFunction function1 = new DegreeFunction();
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function.andThen(function1), 1, 20, 10);
        System.out.println(list.toString());
        double delta = 0.1;
        assertEquals(1.0, list.apply(1), delta);
        assertEquals(2.08, list.apply(9), delta);
        assertEquals(2.15, list.apply(10), delta);
        assertEquals(1.58, list.apply(4), delta);
        assertEquals(1.709, list.apply(5), delta);
    }
}