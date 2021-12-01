package ru.ssau.tk.const1.labs.concurrent;

import ru.ssau.tk.const1.labs.functions.ConstantFunction;
import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;

public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);
        Thread thread1 = new Thread(new MultiplyingTask(function));
        Thread thread2 = new Thread(new MultiplyingTask(function));
        Thread thread3 = new Thread(new AddingTask(function));
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
