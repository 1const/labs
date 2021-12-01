package ru.ssau.tk.const1.labs.exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {
    private static final long serialVersionUID = -3831878693526438950L;

    public DifferentLengthOfArraysException() {
    }

    public DifferentLengthOfArraysException(String message) {
        throw new RuntimeException(message);
    }
}
