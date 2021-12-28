package ru.ssau.tk.const1.labs.functions.factory;

import ru.ssau.tk.const1.labs.functions.ArrayTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.MathFunction;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction createFromArray(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Override
    public TabulatedFunction createFromFunction(MathFunction source, double xFrom, double xTo, int count) {
        return new LinkedListTabulatedFunction(source, xFrom, xTo, count);
    }
}