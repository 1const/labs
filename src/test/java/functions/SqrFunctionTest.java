package functions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqrFunctionTest {
    @Test
    public void testApply() {
        SqrFunction func = new SqrFunction();
        assertEquals(Math.sqrt(4), func.apply(4), 0);
        assertEquals(Math.sqrt(5), func.apply(5), 0);
        assertEquals(Math.sqrt(10), func.apply(10), 0);
    }
}