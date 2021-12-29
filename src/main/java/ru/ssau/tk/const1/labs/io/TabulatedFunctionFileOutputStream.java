package ru.ssau.tk.const1.labs.io;

import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        LinkedListTabulatedFunction arrayFunction = new LinkedListTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkedFunction = new LinkedListTabulatedFunction(xValues, yValues);
        try (BufferedOutputStream outputStreamToArray = new BufferedOutputStream(new FileOutputStream("output/binary function.bin"))) {
            FunctionsIO.writeTabulatedFunction(outputStreamToArray, arrayFunction);
            FunctionsIO.writeTabulatedFunction(outputStreamToArray, linkedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}