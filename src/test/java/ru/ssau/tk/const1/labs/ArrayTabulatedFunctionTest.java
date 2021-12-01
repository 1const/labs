package ru.ssau.tk.const1.labs;

import org.junit.Assert;
import org.junit.Test;
import ru.ssau.tk.const1.labs.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.const1.labs.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.const1.labs.functions.*;
import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayTabulatedFunctionTest {
    @Test
    public void testDefaultFunctionsConstructor1() {
        MathFunction function = new SqrFunction();
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr);
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
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction arr2 = new ArrayTabulatedFunction(xValues, yValues);
        System.out.println(arr2);
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
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr);
        double delta = 0.1;                                             // скорее всего большая погрешность из-за маленького количества count
        assertEquals(1.0, arr.apply(1), delta);             //левая граница
        assertEquals(3.0, arr.apply(9), delta);             //правая граница
        assertEquals(2.0, arr.apply(4), delta);             // не табличное значение внутри границы
        assertEquals(2.23, arr.apply(5), delta);            // табличное значение внутри границы
    }

    @Test
    public void testInsert() {
        MathFunction function = new SqrFunction();
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr);
        arr.insert(2, 2);
        System.out.println(arr);
        arr.remove(2);
        System.out.println(arr);
    }

    @Test
    public void testExceptions() {
        double[] xValues = {1, 2};
        double[] yValues = {1, 2, 3};
        Assert.assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
        double[] xValues2 = {1};
        double[] yValues2 = {1};
        Assert.assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(xValues2, yValues2));
        double[] xValues3 = {3, 2, 1};
        double[] yValues3 = {1, 2, 3};
        Assert.assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(xValues3, yValues3));
        MathFunction function = new SqrFunction();
        Assert.assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(function, 9., 2., 5));
        Assert.assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(function, 1., 9., 1));
        double[] xValues4 = {1, 2, 3, 4, 5};
        double[] yValues4 = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction arr2 = new ArrayTabulatedFunction(xValues4, yValues4);
        Assert.assertThrows(IllegalArgumentException.class, () -> arr2.getX(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> arr2.getY(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> arr2.setY(-1, 2));
        Assert.assertThrows(IllegalArgumentException.class, () -> arr2.remove(100));
    }

    @Test
    public void testIterator() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction arr2 = new ArrayTabulatedFunction(xValues, yValues);
        for (Point p : arr2) {
            System.out.println("foreach:" + p.x + ", " + p.y);
        }
        Iterator<Point> iterator = arr2.iterator();
        while (iterator.hasNext()) {
            Point p = iterator.next();
            System.out.println("while:" + p.x + ", " + p.y);
        }
    }

    @Test
    public void testFactory() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        TabulatedFunction array = arrayFactory.create(xValues, yValues);
        TabulatedFunction list = listFactory.create(xValues, yValues);
    }
}