package ru.ssau.tk.const1.labs.functions.factory;

import ru.ssau.tk.const1.labs.functions.MathFunction;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction createFromArray(double[] xValues, double[] yValues);
    TabulatedFunction createFromFunction(MathFunction source, double xFrom, double xTo, int count);
}