package ru.ssau.tk.const1.labs.io;

import ru.ssau.tk.const1.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.const1.labs.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream("input/binary function.bin"))) {
            ArrayTabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
            System.out.println(FunctionsIO.readTabulatedFunction(bufferedInputStream, arrayFactory).toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции:");
            LinkedListTabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(linkedListFactory);
            System.out.println(tabulatedDifferentialOperator.derive(FunctionsIO.readTabulatedFunction(bufferedReader, linkedListFactory)).toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
