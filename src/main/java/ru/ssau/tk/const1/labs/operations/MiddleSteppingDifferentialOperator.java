package ru.ssau.tk.const1.labs.operations;

import ru.ssau.tk.const1.labs.functions.MathFunction;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> ((function.apply(x + step) - function.apply(x - step)) / (2 * step));
    }
}
