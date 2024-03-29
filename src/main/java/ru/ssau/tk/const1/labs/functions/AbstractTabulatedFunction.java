package ru.ssau.tk.const1.labs.functions;

import ru.ssau.tk.const1.labs.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.const1.labs.exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX,
                                 double leftY, double rightY) {
        return leftY + ((rightY - leftY) * (x - leftX)) / (rightX - leftX);
    }

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException();
        }
    }

    protected static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i] >= xValues[i + 1]) {
                throw new ArrayIsNotSortedException();
            }
        }
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        return indexOfX(x) == -1 ? interpolate(x,
                floorIndexOfX(x)) : getY(indexOfX(x));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName() + " size = " + getCount() + "\n");
        for (Point i : this) {
            str.append("[").append(i.x).append("; ").append(i.y).append("]").append("\n");
        }
        return str.toString();
    }
}
