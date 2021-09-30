package functions;

import java.util.Arrays;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction{
    private int count;
    private Node head;
    public LinkedListTabulatedFunction(double[] xValues, double[] yValues){
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }
    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double step = (xTo - xFrom) / (count - 1);
        double tempX = xFrom;
        for (int i = 0; i < count; i++) {
            addNode(tempX, source.apply(tempX));
            tempX += step;
        }
    }
    private void addNode(double x, double y){
        if(count == 0){
            head = new Node(x, y);
            head.next = head;
            head.prev = head;
        }
        else{
            Node last = new Node(x, y);
            last.next = head;
            last.prev = head.prev;
            head.prev.next = last;
            head.prev = last;
        }
        count++;
    }
    private Node getNode(int index){
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }
        return  temp;
    }
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node temp = head;
        for (int i = 0; i < count; i++) {
            if( head.x == x){
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
            if( head.y == y){
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
        Node temp = head;
        if (x > rightBound()) {
            return count - 1;
        } else if (x < leftBound()) {
            return 0;
        }
        for (int i = 0; i < count; i++) {
            if(temp.x == x){
                return i;
            }
            else if(temp.x > x){
                return i - 1;
            }
            temp = temp.next;
        }
        return 0;                       //костыль
    }

    @Override
    protected double extrapolateLeft(double x) {
        if(count == 1){
            return head.y;
        }
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        if(count == 1){
            return head.y;
        }
        return interpolate(x, count - 2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if(count == 1){
            return getNode(0).y;
        }
        return interpolate(x, getNode(floorIndex).x, getNode(floorIndex + 1).x,
                getNode(floorIndex).y, getNode(floorIndex + 1).y);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        }
        return indexOfX(x) == -1 ? interpolate(x,
                floorIndexOfX(x)) : getY(indexOfX(x));
    }
    private Node floorNodeOfX(double x){
        Node temp = head;
        if (x > rightBound()) {
            return head.prev;
        } else if (x < leftBound()) {
            return head;
        }
        for (int i = 0; i < count; i++) {
            if(temp.x == x){
                return temp;
            }
            else if(temp.x > x){
                return temp.prev;
            }
            temp = temp.next;
        }
        return temp;            //костыль
    }

    @Override
    public String toString() {
        Node temp = head;
        String str ="0 node:" + head.toString();
        for (int i = 1; i < count; i++) {
            temp = temp.next;
            str += "\n" + i + " node:" + temp.toString();
        }
        str +="\n" + "----------------------------";
        return str;
    }
}
