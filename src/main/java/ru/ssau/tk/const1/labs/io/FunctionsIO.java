package ru.ssau.tk.const1.labs.io;

import ru.ssau.tk.const1.labs.functions.Point;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.functions.factory.TabulatedFunctionFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public final class FunctionsIO {
    FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point i : function) {
            printWriter.printf("%f %f\n", i.x, i.y);
        }
        printWriter.flush();
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(function.getCount());
        for (Point i : function) {
            dataOutputStream.writeDouble(i.x);
            dataOutputStream.writeDouble(i.y);
        }
        dataOutputStream.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat numberFormatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        try {
            String[] numbers;
            for (int i = 0; i < count; i++) {
                numbers = reader.readLine().split(" ");
                xValues[i] = numberFormatter.parse(numbers[0]).doubleValue();
                yValues[i] = numberFormatter.parse(numbers[1]).doubleValue();
            }
        } catch (ParseException e) {
            throw new IOException(e);
        }
        return factory.createFromArray(xValues, yValues);
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int count = dataInputStream.readInt();
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = dataInputStream.readDouble();
            yValues[i] = dataInputStream.readDouble();
        }
        return factory.createFromArray(xValues, yValues);
    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(stream);
        outputStream.writeObject(function);
        outputStream.flush();
    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream outputStream = new ObjectInputStream(stream);
        return (TabulatedFunction) outputStream.readObject();
    }
}