package ru.ssau.tk.const1.labs;

import org.junit.Assert;
import org.junit.Test;
import ru.ssau.tk.const1.labs.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.ArrayTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.Point;
import ru.ssau.tk.const1.labs.functions.SqrFunction;

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
}
