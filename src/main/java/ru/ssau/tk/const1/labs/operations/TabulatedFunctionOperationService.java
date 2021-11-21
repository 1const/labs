package ru.ssau.tk.const1.labs.operations;

import ru.ssau.tk.const1.labs.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.const1.labs.functions.Point;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount() - 1];
        int j = 0;
        for (Point i : tabulatedFunction) {
            points[j] = i;
            j++;
        }
        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException();
        }
        Point[] aPoints = asPoints(a);
        Point[] bPoints = asPoints(b);
        double[] xValues = new double[aPoints.length];
        double[] yValues = new double[aPoints.length];
        for (int i = 0; i < xValues.length; i++) {
            if (aPoints[i].x == bPoints[i].x) {
                xValues[i] = aPoints[i].x;
            } else {
                throw new InconsistentFunctionsException();
            }
        }
        for (int i = 0; i < xValues.length; i++) {
            yValues[i] = operation.apply(aPoints[i].y, bPoints[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction subtraction(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    public TabulatedFunction addition(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction divide(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }
}