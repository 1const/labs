package functions;

import org.junit.Test;

import java.util.Arrays;

public class tests {
    @Test
    public void test(){
        Node[] arr = new Node[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = new Node(i, i);
        }
        System.out.println(arr.toString());
        Node x = new Node(1, 1);
        System.out.println(Arrays.binarySearch(arr, x));
    }
    @Test
    public void test2(){
        double[] arr = {1, 2, 3 ,4 };
        System.out.println(Math.abs(Arrays.binarySearch(arr, 3) + 2));
    }
}

