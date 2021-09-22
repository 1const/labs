package functions;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdentityFunctionTest {
    @Test
    public void test(){
        MathFunction identity = new IdentityFunction();
        System.out.println(identity.apply(5));
    }
}