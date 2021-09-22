package functions;

import org.junit.Test;

import static org.junit.Assert.*;

public class Degree_2over3_FunctionTest {
    @Test
    public void test(){
        Degree_2over3_Function degree = new Degree_2over3_Function();
        System.out.println(degree.apply(5));
    }
}