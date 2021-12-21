package ru.ssau.tk.const1.labs.concurrent;

import ru.ssau.tk.const1.labs.functions.TabulatedFunction;

public class ReadWriteTask implements Runnable {
    private final TabulatedFunction function;

    public ReadWriteTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
            double x = function.getX(i);
            double y;
            synchronized (function) {
                y = function.getY(i);
                System.out.printf("%s, before write: i = %d, x = %f, y = %f \n", Thread.currentThread().getName(), i, x, y);
                function.setY(i, y + 1);
            }
            System.out.printf("%s, after write: i = %d, x = %f, y = %f \n", Thread.currentThread().getName(), i, x, y);
        }

    }
}
