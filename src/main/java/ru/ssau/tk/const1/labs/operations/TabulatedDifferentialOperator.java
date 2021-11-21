package ru.ssau.tk.const1.labs.operations;

import ru.ssau.tk.const1.labs.functions.MathFunction;
import ru.ssau.tk.const1.labs.functions.Point;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = new TabulatedFunctionOperationService().asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];
        int trueLength = points.length - 1;
        for (int i = 0; i < trueLength; i++) {
            xValues[i] = points[i].x;
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }
        xValues[trueLength] = points[trueLength].x;
        yValues[trueLength] = yValues[trueLength - 1];
        return factory.create(xValues, yValues);
    }
}
