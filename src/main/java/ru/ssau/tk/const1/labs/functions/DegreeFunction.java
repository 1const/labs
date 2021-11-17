package ru.ssau.tk.const1.labs.functions;

public class DegreeFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.cbrt(x * x);
    }
}
