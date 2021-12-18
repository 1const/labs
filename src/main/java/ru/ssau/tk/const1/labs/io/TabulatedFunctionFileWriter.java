package ru.ssau.tk.const1.labs.io;

import ru.ssau.tk.const1.labs.functions.ArrayTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkedFunction = new LinkedListTabulatedFunction(xValues, yValues);
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("output/array function.txt"))) {
            FunctionsIO.writeTabulatedFunction(bufferedWriter, arrayFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(
                             new FileWriter("output/linked list function.txt"))) {
            FunctionsIO.writeTabulatedFunction(bufferedWriter, linkedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
