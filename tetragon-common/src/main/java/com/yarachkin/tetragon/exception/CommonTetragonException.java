package com.yarachkin.tetragon.exception;

public class CommonTetragonException extends Exception {
    public CommonTetragonException() {
    }

    public CommonTetragonException(String message) {
        super(message);
    }

    public CommonTetragonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonTetragonException(Throwable cause) {
        super(cause);
    }
}
