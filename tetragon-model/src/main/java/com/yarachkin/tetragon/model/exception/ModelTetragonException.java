package com.yarachkin.tetragon.model.exception;

public class ModelTetragonException extends Exception {
    public ModelTetragonException() {
    }

    public ModelTetragonException(String message) {
        super(message);
    }

    public ModelTetragonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelTetragonException(Throwable cause) {
        super(cause);
    }
}
