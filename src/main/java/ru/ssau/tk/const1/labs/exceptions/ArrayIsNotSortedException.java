package ru.ssau.tk.const1.labs.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    private static final long serialVersionUID = 2102299711348610932L;

    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String message) {
        throw new RuntimeException(message);
    }
}
