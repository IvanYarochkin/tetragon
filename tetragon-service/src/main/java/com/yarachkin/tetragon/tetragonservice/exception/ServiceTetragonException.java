package com.yarachkin.tetragon.tetragonservice.exception;

public class ServiceTetragonException extends Exception {
    public ServiceTetragonException() {
    }

    public ServiceTetragonException(String message) {
        super(message);
    }

    public ServiceTetragonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceTetragonException(Throwable cause) {
        super(cause);
    }
}
