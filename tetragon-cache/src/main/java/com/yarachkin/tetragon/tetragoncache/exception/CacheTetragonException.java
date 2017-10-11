package com.yarachkin.tetragon.tetragoncache.exception;

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

    public CacheTetragonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
