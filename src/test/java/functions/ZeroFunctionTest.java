package functions;

import org.junit.Test;

import static org.junit.Assert.*;

public class ZeroFunctionTest {
    @Test
    public void test(){
        ConstantFunction unitFunction = new ZeroFunction();
        System.out.println(unitFunction.apply(7));
    }
}