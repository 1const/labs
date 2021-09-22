package functions;

public class ConstantFunction implements MathFunction{
    private final double CONST;

    public ConstantFunction(double CONST) {
        this.CONST = CONST;
    }

    public double getCONST() {
        return CONST;
    }

    @Override
    public double apply(double x) {
        return CONST;
    }
}
