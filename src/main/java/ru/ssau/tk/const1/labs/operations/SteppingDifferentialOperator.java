package ru.ssau.tk.const1.labs.operations;

import ru.ssau.tk.const1.labs.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        if (Double.isFinite(step) && step != 0) {
            this.step = step;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
