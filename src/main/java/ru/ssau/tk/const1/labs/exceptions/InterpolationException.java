package ru.ssau.tk.const1.labs.exceptions;

public class InterpolationException extends RuntimeException {
    private static final long serialVersionUID = -2668690789632966825L;

    public InterpolationException() {
    }

    public InterpolationException(String message) {
        throw new RuntimeException(message);
    }
}
