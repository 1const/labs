package ru.ssau.tk.const1.labs.exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {
    public DifferentLengthOfArraysException() {
    }

    public DifferentLengthOfArraysException(String message) {
        throw new RuntimeException(message);
    }
}
