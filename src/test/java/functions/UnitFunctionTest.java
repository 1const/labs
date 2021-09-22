package functions;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitFunctionTest {
    @Test
    public void test(){
        ConstantFunction unitFunction = new UnitFunction();
        System.out.println(unitFunction.apply(7));
    }
}