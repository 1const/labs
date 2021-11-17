package ru.ssau.tk.const1.labs.exceptions;

public class InterpolationException extends RuntimeException {
    public InterpolationException() {
    }

    public InterpolationException(String message) {
        throw new RuntimeException(message);
    }
}
