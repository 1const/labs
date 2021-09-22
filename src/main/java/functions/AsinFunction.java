package functions;
import java.lang.Math;
public class AsinFunction implements MathFunction{
    @Override
    public double apply(double x) {
        return Math.asin(x);
    }
}
