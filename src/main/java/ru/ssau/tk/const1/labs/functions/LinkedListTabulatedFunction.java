package ru.ssau.tk.const1.labs.functions;


import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.const1.labs.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {
    private static final long serialVersionUID = 2086469304634636410L;
    private int count;
    private Node head;

    static class Node implements Serializable{
        private static final long serialVersionUID = -252382748565738973L;
        public double x;
        public double y;
        public Node next;
        public Node prev;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        if (xValues.length < 2) {
            throw new IllegalArgumentException("xValues.length < 2");
        }
        checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("From >= To");
        }
        if (count < 2) {
            throw new IllegalArgumentException("count < 2");
        }
        double step = (xTo - xFrom) / (count - 1);
        double tempX = xFrom;
        for (int i = 0; i < count; i++) {
            addNode(tempX, source.apply(tempX));
            tempX += step;
        }
    }

    private void addNode(double x, double y) {
        if (count == 0) {
            head = new Node(x, y);
            head.next = head;
            head.prev = head;
        } else {
            Node last = new Node(x, y);
            last.next = head;
            last.prev = head.prev;
            head.prev.next = last;
            head.prev = last;
        }
        count++;
    }

    private Node getNode(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }
        return temp;
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
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node temp = head;
        for (int i = 0; i < count; i++) {
            if (temp.x == x) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node temp = head;
        for (int i = 0; i < count; i++) {
            if (temp.y == y) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("x < leftBound()");
        }
        Node temp = head;
        if (x > rightBound()) {
            return count - 1;
        }
        for (int i = 0; i < count; i++) {
            if (temp.x == x) {
                return i;
            } else if (temp.x > x) {
                return i - 1;
            }
            temp = temp.next;
        }
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return head.getY();
        }
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, count - 2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < getNode(floorIndex).x || x > getNode(floorIndex + 1).x) {
            throw new InterpolationException();
        }
        return interpolate(x, getNode(floorIndex).x, getNode(floorIndex + 1).x,
                getNode(floorIndex).y, getNode(floorIndex + 1).y);
    }

    protected double interpolate(double x, Node floorNodeOfX) {
        return interpolate(x, floorNodeOfX.x, floorNodeOfX.next.x,
                floorNodeOfX.y, floorNodeOfX.next.y);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        }
        return indexOfX(x) == -1 ? interpolate(x,
                floorNodeOfX(x)) : getY(indexOfX(x));
    }

    private Node floorNodeOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("x < leftBound()");
        }
        Node temp = head;
        if (x > rightBound()) {
            return head.prev;
        }
        for (int i = 0; i < count; i++) {
            if (temp.x == x) {
                return temp;
            } else if (temp.x > x) {
                return temp.prev;
            }
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public void insert(double x, double y) {
        Node temp = head;
        for (int i = 1; i < count; i++) {
            if (temp.x == x) {
                temp.y = y;
                break;
            } else if (temp.x > x) {
                Node newNode = new Node(x, y);
                newNode.next = temp;
                newNode.prev = temp.prev;
                temp.prev.next = newNode;
                temp.prev = newNode;
                count++;
                break;
            }
            temp = temp.next;
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        Node temp = head;

        for (int i = 0; i < count; i++) {
            if (i == index) {
                if (index == 0) {
                    head = temp.next;
                }
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                temp.next = null;
                temp.prev = null;
                count--;
                break;
            }
            temp = temp.next;
        }
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private int i = 0;
            Node node = head;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    Point point = new Point(node.x, node.y);
                    node = node.next;
                    i++;
                    return point;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
