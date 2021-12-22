package ru.ssau.tk.const1.labs;

import org.junit.Assert;
import org.junit.Test;
import ru.ssau.tk.const1.labs.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunctionTest {
    @Test
    public void testIterator() {
        double delta = 0.001;
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new ArrayTabulatedFunction(new SqrFunction(), 1, 10, 10));
        Iterator<Point> iterator = synchronizedTabulatedFunction.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            Assert.assertEquals(point.x, synchronizedTabulatedFunction.getX(i), delta);
            Assert.assertEquals(point.y, synchronizedTabulatedFunction.getY(i), delta);
            i++;
        }
        Assert.assertThrows(NoSuchElementException.class, iterator::next);
        Assert.assertEquals(i, 10);
        i = 0;
        for (Point point : synchronizedTabulatedFunction) {
            Assert.assertEquals(point.x, synchronizedTabulatedFunction.getX(i), delta);
            Assert.assertEquals(point.y, synchronizedTabulatedFunction.getY(i), delta);
            i++;
        }
        Assert.assertEquals(i, 10);
    }

    @Test
    public void testGetCount() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new LinkedListTabulatedFunction(new SqrFunction(), 1, 10, 10));
        Assert.assertEquals(synchronizedTabulatedFunction.getCount(), 10);
    }

    @Test
    public void testGetX() {
        double delta = 0.001;
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new ArrayTabulatedFunction(new SqrFunction(), 1, 10, 10));
        Assert.assertEquals(synchronizedTabulatedFunction.getX(1), 2.0, delta);
        Assert.assertEquals(synchronizedTabulatedFunction.getX(4), 5.0, delta);
        Assert.assertEquals(synchronizedTabulatedFunction.getX(7), 8.0, delta);
    }

    @Test
    public void testGetY() {
        double delta = 0.001;
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new LinkedListTabulatedFunction(new SqrFunction(), 1, 10, 10));
        Assert.assertEquals(synchronizedTabulatedFunction.getY(2), 1.732, delta);
        Assert.assertEquals(synchronizedTabulatedFunction.getY(5), 2.449, delta);
        Assert.assertEquals(synchronizedTabulatedFunction.getY(8), 3.0, delta);
    }

    @Test
    public void testSetY() {
        double delta = 0.001;
        double[] xValues = {2, 4, 6, 8, 10};
        double[] yValues = {4, 16, 36, 64, 100};
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new ArrayTabulatedFunction(xValues, yValues));
        synchronizedTabulatedFunction.setY(0, 0);
        synchronizedTabulatedFunction.setY(2, 0);
        synchronizedTabulatedFunction.setY(3, 0);
        Assert.assertEquals(synchronizedTabulatedFunction.getY(0), 0.0, delta);
        Assert.assertEquals(synchronizedTabulatedFunction.getY(2), 0.0, delta);
        Assert.assertEquals(synchronizedTabulatedFunction.getY(3), 0.0, delta);
    }

    @Test
    public void testIndexOfX() {
        double[] xValues = {2, 4, 6, 8, 10};
        double[] yValues = {4, 16, 36, 64, 100};
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new LinkedListTabulatedFunction(xValues, yValues));
        Assert.assertEquals(synchronizedTabulatedFunction.indexOfX(2), 0);
        Assert.assertEquals(synchronizedTabulatedFunction.indexOfX(6), 2);
        Assert.assertEquals(synchronizedTabulatedFunction.indexOfX(12), -1);
    }

    @Test
    public void testIndexOfY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new ArrayTabulatedFunction(new IdentityFunction(), 1, 10, 10));
        Assert.assertEquals(synchronizedTabulatedFunction.indexOfY(2), 1);
        Assert.assertEquals(synchronizedTabulatedFunction.indexOfY(5), 4);
        Assert.assertEquals(synchronizedTabulatedFunction.indexOfY(7), 6);
    }

    @Test
    public void testLeftBound() {
        double delta = 0.001;
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new LinkedListTabulatedFunction(new SqrFunction(), 1, 10, 10));
        Assert.assertEquals(synchronizedTabulatedFunction.leftBound(), 1.0, delta);
    }

    @Test
    public void testRightBound() {
        double delta = 0.001;
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new LinkedListTabulatedFunction(new SqrFunction(), 1, 10, 10));
        Assert.assertEquals(synchronizedTabulatedFunction.rightBound(), 10.0, delta);
    }

    @Test
    public void testApply() {
        double delta = 0.001;
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new ArrayTabulatedFunction(new SqrFunction(), 1, 10, 10));
        Assert.assertEquals(synchronizedTabulatedFunction.apply(5), 2.236, delta);
        Assert.assertEquals(synchronizedTabulatedFunction.apply(7), 2.645, delta);
    }

    @Test
    public void testDoSynchronously() {
        double delta = 0.001;
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(
                new LinkedListTabulatedFunction(xValues, yValues)
        );
        Assert.assertEquals((int) synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::getCount), 5.0, delta);
        Assert.assertEquals((double) synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::rightBound), 5.0, delta);
    }
}
