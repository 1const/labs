package ru.ssau.tk.const1.labs.operations;

import ru.ssau.tk.const1.labs.functions.MathFunction;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> ((function.apply(x) - function.apply(x - step)) / step);
    }
}
