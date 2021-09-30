package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, xValues.length);
        count = xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
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
        if (x > rightBound()) {
            return count - 1;
        } else if (x < leftBound()) {
            return 0;
        } else if (Arrays.binarySearch(xValues, x) < 0) {
            return Math.abs(Arrays.binarySearch(xValues, x) + 2);
        }
        return Arrays.binarySearch(xValues, x);
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, count - 2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return yValues[0];
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
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
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
    private void insertIntoArr(double[] from, double[] in, int index, double value){
        System.arraycopy(from, 0, in, 0, index);
        in[index] = value;
        System.arraycopy(from, index , in, index + 1, from.length - index);
    }

    @Override
    public void remove(int index) {
        double[] upXValues = new double[count - 1];
        double[] upYValues = new double[count - 1];
        removeIntoArr(xValues, upXValues, index);
        removeIntoArr(yValues, upYValues, index);
        xValues = upXValues;
        yValues = upYValues;
        count++;
    }
    private void removeIntoArr(double[] from, double[] in, int index){
        System.arraycopy(from, 0, in, 0, index - 1);
        System.arraycopy(from, index , in, index - 1, from.length - index);
    }
    @Override
    public String toString(){
        return Arrays.toString(xValues) + '\n' +  Arrays.toString(yValues);
    }
}
