package ru.ssau.tk.const1.labs.functions.factory;

import ru.ssau.tk.const1.labs.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}