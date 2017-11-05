package com.yarachkin.tetragon.exception;

public class IOTetragonException extends Exception {
    public IOTetragonException() {
    }

    public IOTetragonException(String message) {
        super(message);
    }

    public IOTetragonException(String message, Throwable cause) {
        super(message, cause);
    }

    public IOTetragonException(Throwable cause) {
        super(cause);
    }
}
