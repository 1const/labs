package ru.ssau.tk.const1.labs.concurrent;

import ru.ssau.tk.const1.labs.functions.TabulatedFunction;

public class AddingTask implements Runnable {
    private final TabulatedFunction function;

    public AddingTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
            double x = function.getX(i);
            double y;
            synchronized (function) {
                y = function.getY(i);
                System.out.printf("%s, i = %d, x = %f, old y = %f \n", Thread.currentThread().getName(), i, x, y);
                function.setY(i, y + 3);
            }
            System.out.printf("%s, i = %d, x = %f, new y = %f \n", Thread.currentThread().getName(), i, x, y);
        }

    }
}
