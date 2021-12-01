package ru.ssau.tk.const1.labs.concurrent;

import ru.ssau.tk.const1.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.const1.labs.functions.TabulatedFunction;
import ru.ssau.tk.const1.labs.functions.ZeroFunction;

import java.util.ArrayList;
import java.util.List;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        ReadWriteTask readWriteTask = new ReadWriteTask(function);
        List<Thread> listOfThread = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            listOfThread.add(new Thread(readWriteTask, "thread-" + i));
        }
        for(Thread t : listOfThread){
            t.start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
