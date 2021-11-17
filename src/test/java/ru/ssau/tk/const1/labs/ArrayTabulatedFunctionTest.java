package ru.ssau.tk.const1.labs;

import org.junit.Test;
import ru.ssau.tk.const1.labs.functions.ArrayTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.MathFunction;
import ru.ssau.tk.const1.labs.functions.SqrFunction;

import static org.junit.Assert.*;

public class ArrayTabulatedFunctionTest {
    @Test
    public void testDefaultFunctionsConstructor1(){
        MathFunction function = new SqrFunction();
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr.toString());
        System.out.println(arr.getCount() + " - length");
        arr.setY(1, 4);
        System.out.println(arr.getX(2) + "-  X");
        System.out.println(arr.indexOfY(3) + "-  index of y");
        System.out.println(arr.getY(1) + "- new Y");
        System.out.println(arr.leftBound() + " - leftBound");
        System.out.println(arr.rightBound() + " - rightBound");
        System.out.println(arr.indexOfX(1) + " indexOf 1");
    }
    @Test
    public void testDefaultFunctionsConstructor2() {
        double[] xValues ={1, 2, 3, 4, 5};
        double[] yValues ={1, 2, 3, 4, 5};
        ArrayTabulatedFunction arr2 = new ArrayTabulatedFunction(xValues, yValues);
        System.out.println(arr2.toString());
        System.out.println(arr2.getCount() + " - length");
        arr2.setY(1, 4);
        System.out.println(arr2.getY(1) + "- new Y");
        System.out.println(arr2.leftBound() + " - leftBound");
        System.out.println(arr2.rightBound() + " - rightBound");
        System.out.println(arr2.indexOfX(1) + " indexOf 1");
    }
    @Test
    public void testApply(){
        MathFunction function = new SqrFunction();
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr.toString());
        double delta = 0.1;                                             // скорее всего большая погрешность из-за маленького количества count
        assertEquals(1.0, arr.apply(1), delta );             //левая граница
        assertEquals(3.0, arr.apply(9), delta );             //правая граница
        assertEquals(2.0, arr.apply(4), delta );             // не табличное значение внутри границы
        assertEquals(2.23, arr.apply(5), delta );            // табличное значение внутри границы
    }
    @Test
    public void testInsert(){
        MathFunction function = new SqrFunction();
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr.toString());
        arr.insert(2, 2);
        System.out.println(arr.toString());
        arr.remove(2);
        System.out.println(arr.toString());
    }
}