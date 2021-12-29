package ru.ssau.tk.const1.labs.io;

import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListFunctionTabulatedSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("/serialized linked list functions.bin"))) {
            double[] xValues = {1, 2, 3, 4, 5};
            double[] yValues = {1, 4, 9, 16, 25};
            LinkedListTabulatedFunction listFunction = new LinkedListTabulatedFunction(xValues, yValues);
            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator();
            TabulatedFunction firstDifferential = tabulatedDifferentialOperator.derive(listFunction);
            TabulatedFunction secondDifferential = tabulatedDifferentialOperator.derive(firstDifferential);
            FunctionsIO.serialize(bufferedOutputStream, listFunction);
            FunctionsIO.serialize(bufferedOutputStream, firstDifferential);
            FunctionsIO.serialize(bufferedOutputStream, secondDifferential);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("/serialized linked list functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
