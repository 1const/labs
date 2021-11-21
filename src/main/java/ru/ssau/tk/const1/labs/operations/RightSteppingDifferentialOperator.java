package ru.ssau.tk.const1.labs.operations;

import ru.ssau.tk.const1.labs.functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> ((function.apply(x + step) - function.apply(x)) / step);
    }
}