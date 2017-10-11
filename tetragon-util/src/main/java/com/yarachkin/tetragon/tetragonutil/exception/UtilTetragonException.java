package com.yarachkin.tetragon.tetragonutil.exception;

public class UtilTetragonException extends Exception {
    public UtilTetragonException() {
    }

    public UtilTetragonException(String message) {
        super(message);
    }

    public UtilTetragonException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilTetragonException(Throwable cause) {
        super(cause);
    }
}
