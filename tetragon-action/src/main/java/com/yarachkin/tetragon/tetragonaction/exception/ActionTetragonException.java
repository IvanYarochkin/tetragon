package com.yarachkin.tetragon.tetragonaction.exception;

public class ActionTetragonException extends Exception {
    public ActionTetragonException() {
    }

    public ActionTetragonException(String message) {
        super(message);
    }

    public ActionTetragonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionTetragonException(Throwable cause) {
        super(cause);
    }
}
