package ru.ssau.tk.const1.labs.functions;

import java.lang.Math;

public class SqrFunction implements MathFunction {
    @Override

    public double apply(double x) {
        return Math.sqrt(x);
    }
}