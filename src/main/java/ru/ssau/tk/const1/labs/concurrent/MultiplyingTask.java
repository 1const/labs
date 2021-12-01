package ru.ssau.tk.const1.labs.concurrent;

import ru.ssau.tk.const1.labs.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable{
    private TabulatedFunction function;

    public MultiplyingTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
            double x = function.getX(i);
            double y;
            synchronized (this) {
                y = function.getY(i);
                System.out.printf("%s, i = %d, x = %f, old y = %f \n", Thread.currentThread().getName(), i, x, y);
                function.setY(i, y * 10);
            }
            System.out.printf("%s, i = %d, x = %f, new y = %f \n", Thread.currentThread().getName(), i, x, y);
        }

    }
}
