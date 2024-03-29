package ru.ssau.tk.const1.labs.functions;

public interface MathFunction {
    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(afterFunction, this);
    }

    double apply(double x);

}
