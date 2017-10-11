package com.yarachkin.tetragon.tetragondao.exception;

public class DaoTetragonException extends Exception {
    public DaoTetragonException() {
    }

    public DaoTetragonException(String message) {
        super(message);
    }

    public DaoTetragonException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoTetragonException(Throwable cause) {
        super(cause);
    }
}
