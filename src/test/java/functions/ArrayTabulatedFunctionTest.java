package functions;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayTabulatedFunctionTest {
    @Test
    public void testDefaultFunctions(){
        MathFunction function = new SqrFunction();
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr.toString());
        System.out.println(arr.getCount() + " - length");
        System.out.println(arr.floorIndexOfX(4) + " - floorIndex");
        arr.setY(1, 4);
        System.out.println(arr.getY(1) + "- new Y");
        System.out.println(arr.leftBound() + " - leftBound");
        System.out.println(arr.rightBound() + " - rightBound");
        System.out.println(arr.indexOfX(1) + " indexOf 1");
    }
    @Test
    public void testApply(){
        MathFunction function = new SqrFunction();
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr.toString());
        double delta = 0.1;                                             // скорее всего большая погрешность из-за маленького количества count
        assertEquals(1.0, arr.apply(1), delta );             //левая граница
        assertEquals(3.0, arr.apply(9), delta );             //правая граница
        assertEquals(3.16, arr.apply(10), delta );           // выход за границы
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