package functions;

public class Degree_2over3_Function implements MathFunction{
    @Override
    public double apply(double x) {
        return Math.cbrt(x*x);
    }
}
