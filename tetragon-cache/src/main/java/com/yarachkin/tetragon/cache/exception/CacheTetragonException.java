package com.yarachkin.tetragon.cache.exception;

public class CacheTetragonException extends Exception {
    public CacheTetragonException() {
    }

    public CacheTetragonException(String message) {
        super(message);
    }

    public CacheTetragonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheTetragonException(Throwable cause) {
        super(cause);
    }

}
