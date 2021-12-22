package ru.ssau.tk.const1.labs.concurrent;

import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.const1.labs.functions.Point;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction tabulatedFunction;
    private final Object mutex;

    public SynchronizedTabulatedFunction(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
        this.mutex = this;
    }

    public SynchronizedTabulatedFunction(TabulatedFunction tabulatedFunction, Object mutex) {
        this.tabulatedFunction = tabulatedFunction;
        this.mutex = mutex;
    }

    public interface Operation<T> {
        T apply(SynchronizedTabulatedFunction func);
    }

    @Override
    public int getCount() {
        synchronized (mutex) {
            return tabulatedFunction.getCount();
        }
    }

    public <T> T doSynchronously(Operation<? extends T> operation) {
        synchronized (mutex) {
            return operation.apply(this);
        }
    }

    @Override
    public double getX(int index) {
        synchronized (mutex) {
            return tabulatedFunction.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (mutex) {
            return tabulatedFunction.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (mutex) {
            tabulatedFunction.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (mutex) {
            return tabulatedFunction.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (mutex) {
            return tabulatedFunction.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (mutex) {
            return tabulatedFunction.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (mutex) {
            return tabulatedFunction.rightBound();
        }
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        synchronized (mutex) {
            Point[] points = TabulatedFunctionOperationService.asPoints(tabulatedFunction);
            return new Iterator<>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < points.length;
                }

                @Override
                public Point next() {
                    if (hasNext()) {
                        Point point = new Point(points[i].x, points[i].y);
                        i++;
                        return point;
                    }
                    throw new NoSuchElementException();
                }
            };
        }
    }

    @Override
    public double apply(double x) {
        synchronized (mutex) {
            return tabulatedFunction.apply(x);
        }
    }
}
