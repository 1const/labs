package ru.ssau.tk.const1.labs.exceptions;

public class InconsistentFunctionsException extends RuntimeException {
    private static final long serialVersionUID = -9031415963953302475L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        throw new RuntimeException(message);
    }
}
