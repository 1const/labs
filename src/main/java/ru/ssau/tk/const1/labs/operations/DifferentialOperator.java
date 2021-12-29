package ru.ssau.tk.const1.labs.operations;

import ru.ssau.tk.const1.labs.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);

}