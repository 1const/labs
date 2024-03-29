package ru.ssau.tk.const1.labs.functions;

import ru.ssau.tk.const1.labs.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {
    private static final long serialVersionUID = 476017482196043048L;
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        if (xValues.length < 2) {
            throw new IllegalArgumentException("xValues.length < 2");
        }
        checkSorted(xValues);
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, xValues.length);
        count = xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("From >= To");
        }
        if (count < 2) {
            throw new IllegalArgumentException("count < 2");
        }
        xValues = new double[count];
        yValues = new double[count];
        double step = (xTo - xFrom) / (count - 1);
        double j = xFrom;
        for (int i = 0; i < count; i++) {
            yValues[i] = source.apply(j);
            xValues[i] = j;
            j += step;
        }
        this.count = count;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("x < leftBound()");
        }
        if (x > rightBound()) {
            return count - 1;
        } else if (Arrays.binarySearch(xValues, x) < 0) {
            return Math.abs(Arrays.binarySearch(xValues, x) + 2);
        }
        return Arrays.binarySearch(xValues, x);
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, count - 2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < xValues[floorIndex] || x > xValues[floorIndex + 1]) {
            throw new InterpolationException();
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1],
                yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        int returned = Arrays.binarySearch(xValues, x);
        return returned < 0 ? -1 : returned;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public void insert(double x, double y) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                yValues[i] = y;
                break;
            } else if (xValues[i] > x) {
                double[] upXValues = new double[count + 1];
                double[] upYValues = new double[count + 1];
                insertIntoArr(xValues, upXValues, i, x);
                insertIntoArr(yValues, upYValues, i, y);
                xValues = upXValues;
                yValues = upYValues;
                count++;
                break;
            }
        }
    }

    private void insertIntoArr(double[] from, double[] in, int index, double value) {
        System.arraycopy(from, 0, in, 0, index);
        in[index] = value;
        System.arraycopy(from, index, in, index + 1, from.length - index);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        double[] upXValues = new double[count - 1];
        double[] upYValues = new double[count - 1];
        if (index == count - 1) {
            xValues = Arrays.copyOf(xValues, count - 1);
            yValues = Arrays.copyOf(yValues, count - 1);
            count--;
            return;
        } else if (index == 0) {
            xValues = Arrays.copyOfRange(xValues, 1, count);
            yValues = Arrays.copyOfRange(yValues, 1, count);
            count--;
            return;
        }
        removeIntoArr(xValues, upXValues, index);
        removeIntoArr(yValues, upYValues, index);
        xValues = upXValues;
        yValues = upYValues;
        count--;
    }

    private void removeIntoArr(double[] from, double[] in, int index) {
        double[] arrOf = Arrays.copyOfRange(from, 0, index);
        double[] arrTo = Arrays.copyOfRange(from, index + 1, count);
        for (int i = 0; i < arrOf.length; i++) {
            in[i] = arrOf[i];
        }
        int j = 0;
        for (int i = index; i < index + arrTo.length; i++) {
            in[i] = arrTo[j];
            j++;
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    Point point = new Point(xValues[i], yValues[i]);
                    i++;
                    return point;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
