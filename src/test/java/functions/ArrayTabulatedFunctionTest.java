package functions;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayTabulatedFunctionTest {
    @Test
    public void test(){
        MathFunction function = new SqrFunction();
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr.toString());
        System.out.println(arr.getCount() + " - length");
        System.out.println(arr.floorIndexOfX(4) + " - floorIndex");
        arr.setY(1, 4);
        System.out.println(arr.getY(1) + "- new Y");

    }
    @Test
    public void test2(){
        MathFunction function = new SqrFunction();
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(function, 1., 9., 5);
        System.out.println(arr.extrapolateLeft(2) + "- extrapolateLeft");
        System.out.println(arr.extrapolateRight(2) + "- extrapolateRight");
    }
}