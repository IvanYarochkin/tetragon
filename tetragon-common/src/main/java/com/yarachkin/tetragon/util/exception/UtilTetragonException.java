package com.yarachkin.tetragon.util.exception;

public final class UtilTetragonException extends Exception {
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
