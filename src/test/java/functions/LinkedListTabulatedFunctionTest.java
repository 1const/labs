package functions;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTabulatedFunctionTest {
    @Test
   public void test(){
        MathFunction function = new SqrFunction();
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function, 1., 9., 5);
        System.out.println(list.toString());
        System.out.println("tests: ");
        System.out.println(list.floorIndexOfX(3) + " - floorIndexOf3");
        System.out.println(list.floorIndexOfX(4) + " - floorIndexOf4");
        System.out.println(list.leftBound() + " - leftBound");
        System.out.println(list.rightBound() + " - rightBound");
        System.out.println(list.indexOfX(1) + " -index of non-existent x");
        System.out.println(list.indexOfX(3) + " -index of existent x");
    }
    @Test
    public void test2() {
        MathFunction function = new SqrFunction();
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(function, 1., 9., 5);
        System.out.println(list.toString());
        double delta = 0.1;                                              // скорее всего большая погрешность из-за маленького количества count
        assertEquals(1.0, list.apply(1), delta );             //левая граница
        assertEquals(3.0, list.apply(9), delta );             //правая граница
        assertEquals(3.16, list.apply(10), delta );           // выход за границы
        assertEquals(2.0, list.apply(4), delta );            // не табличное значение внутри границы
        assertEquals(2.23, list.apply(5), delta );            // табличное значение внутри границы
        //assertEquals(2.23, list.apply(-4), 0.4 );                         //sqrtX >= 0
    }
}