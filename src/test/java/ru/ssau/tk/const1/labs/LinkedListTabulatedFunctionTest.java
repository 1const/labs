package ru.ssau.tk.const1.labs;

import org.junit.Assert;
import org.junit.Test;
import ru.ssau.tk.const1.labs.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.const1.labs.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.const1.labs.functions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedListTabulatedFunctionTest {
    @Test
    public void testDefaultFunctionsConstructor1() {
        MathFunction function = new SqrFunction();
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function, 1., 9., 5);
        System.out.println(list);
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
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function, 1., 9., 5);
        System.out.println(list);
        double delta = 0.1;                                              // скорее всего большая погрешность из-за маленького количества count
        assertEquals(1.0, list.apply(1), delta);             //левая граница
        assertEquals(3.0, list.apply(9), delta);             //правая граница
        assertEquals(2.0, list.apply(4), delta);            // не табличное значение внутри границы
        assertEquals(2.23, list.apply(5), delta);            // табличное значение внутри границы
    }

    @Test
    public void testInsert() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        LinkedListTabulatedFunction arr = new LinkedListTabulatedFunction(xValues, yValues);
        System.out.println("До изменений");
        System.out.println(arr);

        arr.insert(2.5, 2.5);
        assertEquals(6, arr.getCount(), 0);
        System.out.println("после добавления (2.5, 2.5)");
        System.out.println(arr);

        arr.remove(2);
        assertEquals(5, arr.getCount(), 0);
        System.out.println("после удаления точки (2.5, 2.5)");
        System.out.println(arr);

        arr.insert(1, 2);
        assertEquals(5, arr.getCount(), 0);
        System.out.println("после добавления (1, 2)");
        System.out.println(arr);

        arr.remove(0);
        assertEquals(4, arr.getCount(), 0);
        System.out.println("после удаления точки в начале");
        System.out.println(arr);

        arr.insert(5, 8);
        assertEquals(4, arr.getCount(), 0);
        System.out.println("после добавления (5, 6)");
        System.out.println(arr);

        arr.remove(3);
        assertEquals(3, arr.getCount(), 0);
        System.out.println("после удаления точки в конце");
        System.out.println(arr);
    }

    @Test
    public void testApply2() {
        MathFunction function = new SqrFunction();
        MathFunction function1 = new DegreeFunction();
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function.andThen(function1), 1, 20, 10);
        System.out.println(list);
        double delta = 0.1;
        assertEquals(1.0, list.apply(1), delta);
        assertEquals(2.08, list.apply(9), delta);
        assertEquals(2.15, list.apply(10), delta);
        assertEquals(1.58, list.apply(4), delta);
        assertEquals(1.709, list.apply(5), delta);
    }

    @Test
    public void testExceptions() {
        double[] xValues = {1, 2};
        double[] yValues = {1, 2, 3};
        Assert.assertThrows(DifferentLengthOfArraysException.class, () -> new LinkedListTabulatedFunction(xValues, yValues));
        double[] xValues2 = {1};
        double[] yValues2 = {1};
        Assert.assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(xValues2, yValues2));
        double[] xValues3 = {3, 2, 1};
        double[] yValues3 = {1, 2, 3};
        Assert.assertThrows(ArrayIsNotSortedException.class, () -> new LinkedListTabulatedFunction(xValues3, yValues3));
        MathFunction function = new SqrFunction();
        Assert.assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(function, 9., 2., 5));
        Assert.assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(function, 1., 9., 1));
        double[] xValues4 = {1, 2, 3, 4, 5};
        double[] yValues4 = {1, 2, 3, 4, 5};
        LinkedListTabulatedFunction arr2 = new LinkedListTabulatedFunction(xValues4, yValues4);
        Assert.assertThrows(IllegalArgumentException.class, () -> arr2.getX(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> arr2.getY(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> arr2.setY(-1, 2));
        Assert.assertThrows(IllegalArgumentException.class, () -> arr2.remove(100));
    }

    @Test
    public void testIterator() {
        double delta = 0.01;
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        LinkedListTabulatedFunction arr2 = new LinkedListTabulatedFunction(xValues, yValues);
        int i = 0;
        for (Point p : arr2) {
            assertEquals(xValues[i], p.x, delta);
            assertEquals(yValues[i], p.y, delta);
            i++;
        }
        assertEquals(arr2.getCount(), i);
        i = 0;
        Iterator<Point> iterator = arr2.iterator();
        while (iterator.hasNext()) {
            Point p = iterator.next();
            assertEquals(xValues[i], p.x, delta);
            assertEquals(yValues[i], p.y, delta);
            i++;
        }
        assertEquals(arr2.getCount(), i);
        Assert.assertThrows(NoSuchElementException.class, iterator::next);
    }
}